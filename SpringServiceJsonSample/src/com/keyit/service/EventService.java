package com.keyit.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.keyit.dto.Event;

@Service("eventService")
@Transactional
public class EventService {

	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(EventService.class);

	public void addEvent(Event event) {
		Session session = this.sessionFactory.getCurrentSession();
		try {

			session.save(event);
			logger.info("Save");
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
	}

	public void deleteEvent(Integer eventId) {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Event event = (Event) session.get(Event.class, eventId);
			if (null != event) {
				// this.deleteRecommendedDishByResID(restaurant.getId());
				// this.deleteOperationHourByResID(restaurantId);
				session.delete(event);
				logger.info("Restaurant Name : " + event.getEventName()
						+ " : has been deleted successfully");
			}

		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}

	}

	public Event getEventById(Integer eventId) {
		Session session = this.sessionFactory.getCurrentSession();
		Event event = new Event();
		try {
			event = (Event) session.get(Event.class, new Integer(eventId));

		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}
		return event;
	}

	public void updateRestaurant(Event event) {

		Session session = this.sessionFactory.getCurrentSession();
		try {
			session.saveOrUpdate(event);
		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}

	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
