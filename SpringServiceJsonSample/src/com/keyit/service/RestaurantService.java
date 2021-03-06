package com.keyit.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keyit.dto.Event;
import com.keyit.dto.OperationHour;
import com.keyit.dto.Promotion;
import com.keyit.dto.RecommendedDish;
import com.keyit.dto.Restaurant;

@Service("restaurantService")
@Transactional
public class RestaurantService {
	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(RestaurantService.class);

	public void addRestaurant(Restaurant restaurant) {
		Session session = this.sessionFactory.getCurrentSession();
		try {

			session.save(restaurant);
			logger.info("Save");
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Restaurant> getAllRestaurants() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Restaurant> restaurants = Collections.emptyList();
		try {
			restaurants = session.createQuery("FROM Restaurant").list();
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
		return restaurants;
	}

	@SuppressWarnings("unchecked")
	public List<Restaurant> searchRestaurants(String restaurantName) {
		Session session = this.sessionFactory.getCurrentSession();
		List<Restaurant> restaurants = Collections.emptyList();
		try {
			if ("".equals(restaurantName)) {
				restaurants = session.createQuery("FROM Restaurant").list();
			} else {
				restaurantName += "%";
				Criteria criteria = session.createCriteria(Restaurant.class);
				criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				criteria.add(Restrictions
						.like("restaurantName", restaurantName));

				restaurants = criteria.list();

			}
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
		return restaurants;
	}

	public void deleteRestaurant(Integer restaurantId) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Restaurant restaurant = (Restaurant) session.get(Restaurant.class,
					restaurantId);
			if (null != restaurant) {
				this.deleteRecommendedDishByResID(restaurant.getId());
				this.deleteOperationHourByResID(restaurantId);
				this.deleteEventByRestaurantId(restaurant.getId());
				this.deletePromotionByRestaurantId(restaurant.getId());

				session.delete(restaurant);
				logger.info("Restaurant Name : "
						+ restaurant.getRestaurantName()
						+ " : has been deleted successfully");
			}

		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}

	}

	public Restaurant getRestaurantById(Integer restaurantId) {
		Session session = this.sessionFactory.getCurrentSession();
		Restaurant restaurant = new Restaurant();
		try {
			restaurant = (Restaurant) session.get(Restaurant.class,
					new Integer(restaurantId));

		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}
		return restaurant;
	}

	public void updateRestaurant(Restaurant restaurant) {

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(restaurant);
		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}

	}

	// This setter will be used by Spring context to inject the sessionFactory
	// instance
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	private void deleteOperationHourByResID(Integer restaurantId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<OperationHour> ohs = session.createQuery(
				"FROM OperationHour where RestaurantID=" + restaurantId).list();
		for (OperationHour oh : ohs) {
			session.delete(oh);
		}
	}

	@SuppressWarnings("unchecked")
	private void deleteRecommendedDishByResID(Integer restaurantId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<RecommendedDish> rds = session.createQuery(
				"FROM RecommendedDish where RestaurantID=" + restaurantId)
				.list();
		for (RecommendedDish recommendedDish : rds) {
			session.delete(recommendedDish);
		}

	}

	private void deleteEventByRestaurantId(Integer restaurantId) {

		Session session = this.sessionFactory.getCurrentSession();

		try {
			Event event = (Event) session.createQuery(
					"FROM Event where RestaurantID=" + restaurantId)
					.uniqueResult();
			session.delete(event);

		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}

	}

	private void deletePromotionByRestaurantId(Integer restaurantId) {

		Session session = this.sessionFactory.getCurrentSession();

		try {
			Promotion promotion = (Promotion) session.createQuery(
					"FROM Promotion where RestaurantID=" + restaurantId)
					.uniqueResult();
			session.delete(promotion);

		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}

	}

}
