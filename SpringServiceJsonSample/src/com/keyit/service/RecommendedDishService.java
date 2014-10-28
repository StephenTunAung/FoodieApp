package com.keyit.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keyit.dto.RecommendedDish;

@Service("recommendedDishService")
@Transactional
public class RecommendedDishService {
	// Session factory injected by spring context
	private SessionFactory sessionFactory;
	private static final Logger logger = LoggerFactory
			.getLogger(RecommendedDishService.class);

	public RecommendedDish getOperationHourById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		RecommendedDish recommendedDish = new RecommendedDish();
		try {
			recommendedDish = (RecommendedDish) session.get(
					RecommendedDish.class, id);
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}

		return recommendedDish;

	}

	public void saveRecommendedDish(RecommendedDish recommendedDish) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.save(recommendedDish);
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<RecommendedDish> getRecommendedDishByResID(Integer restaurantId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<RecommendedDish> recommendedDishs = session.createQuery(
				"FROM RecommendedDish where RestaurantID=" + restaurantId
						+ " ORDER BY RecommendDishID").list();
		return recommendedDishs;
	}

	@SuppressWarnings("unchecked")
	public void deleteRecommendedDishByResID(Integer restaurantId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<RecommendedDish> recommendedDishs = session.createQuery(
				"FROM RecommendedDish where RestaurantID=" + restaurantId)
				.list();
		for (RecommendedDish recommendedDish : recommendedDishs) {
			session.delete(recommendedDish);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
