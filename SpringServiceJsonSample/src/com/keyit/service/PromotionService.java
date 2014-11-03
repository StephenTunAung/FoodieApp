package com.keyit.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keyit.dto.Promotion;

@Service("promotionService")
@Transactional
public class PromotionService {

	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(EventService.class);

	public void addOrUpdateEvent(Promotion promotion) {
		Session session = this.sessionFactory.getCurrentSession();
		try {

			// Event toUpdate = this.getEventById(event.getEventID());
			if (promotion.getPromotionId() != null) {
				session.update(promotion);
			} else {
				session.saveOrUpdate(promotion);
			}
			logger.info("Save");
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
	}

	public void deleteEvent(Integer promotionId) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Promotion promotion = (Promotion) session.get(Promotion.class,
					promotionId);
			if (null != promotion) {
				session.delete(promotion);
				logger.info("Restaurant Name : " + promotion.getPromoName()
						+ " : has been deleted successfully");
			}

		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}

	}

	public Promotion getPromotionById(Integer promotionId) {
		Session session = this.sessionFactory.getCurrentSession();
		Promotion promotion = new Promotion();
		try {
			promotion = (Promotion) session.get(Promotion.class, new Integer(
					promotionId));

		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}
		return promotion;
	}

	public Promotion getPromotionIdByRestaurantId(Integer restaurantId) {
		Session session = this.sessionFactory.getCurrentSession();
		Promotion promotion = new Promotion();
		try {
			promotion = (Promotion) session.createQuery(
					"FROM Promotion where RestaurantID=" + restaurantId)
					.uniqueResult();

		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}
		return promotion;

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
