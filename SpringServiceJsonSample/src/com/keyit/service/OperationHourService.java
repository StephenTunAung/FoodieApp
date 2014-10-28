package com.keyit.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keyit.dto.OperationHour;

@Service("operationHourService")
@Transactional
public class OperationHourService {

	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(FacilityService.class);

	public OperationHour getOperationHourById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		OperationHour operationHour = new OperationHour();
		try {
			operationHour = (OperationHour) session
					.get(OperationHour.class, id);
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}

		return operationHour;

	}

	public void saveOperationHour(OperationHour operationHour) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.save(operationHour);
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<OperationHour> getOperationHourByResID(Integer restaurantId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<OperationHour> ohs = session.createQuery(
				"FROM OperationHour where RestaurantID=" + restaurantId
						+ " ORDER BY RestaurantOpHourID").list();
		return ohs;
	}

	@SuppressWarnings("unchecked")
	public void deleteOperationHourByResID(Integer restaurantId) {
		Session session = this.sessionFactory.getCurrentSession();
		List<OperationHour> ohs = session.createQuery(
				"FROM OperationHour where RestaurantID=" + restaurantId).list();
		for (OperationHour oh : ohs) {
			session.delete(oh);
		}
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
