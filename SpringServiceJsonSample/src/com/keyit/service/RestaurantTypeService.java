package com.keyit.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keyit.dto.RestaurantType;

@Service("restaurantTypeService")
@Transactional
public class RestaurantTypeService {

	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(RestaurantTypeService.class);

	public RestaurantType getRestaurantTypeById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		RestaurantType restaurantType = new RestaurantType();
		try {
			restaurantType = (RestaurantType) session.get(RestaurantType.class,
					id);
		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}
		return restaurantType;
	}

	@SuppressWarnings("unchecked")
	public List<RestaurantType> getAllRestaurantTypes() {
		Session session = this.sessionFactory.getCurrentSession();
		List<RestaurantType> restaurantTypes = Collections.emptyList();

		try {
			restaurantTypes = session.createQuery("from RestaurantType").list();
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
		return restaurantTypes;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
