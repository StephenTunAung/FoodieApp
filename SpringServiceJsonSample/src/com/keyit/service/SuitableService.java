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

import com.keyit.dto.Suitable;

@Service("suitableService")
@Transactional
public class SuitableService {

	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(SuitableService.class);

	public Suitable getSuitableById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		Suitable suitable = new Suitable();
		try {
			suitable = (Suitable) session.get(Suitable.class, id);
		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}
		return suitable;
	}

	@SuppressWarnings("unchecked")
	public List<Suitable> getAllSuitables() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Suitable> suitables = Collections.emptyList();

		try {
			suitables = session.createQuery("from Suitable").list();
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
		return suitables;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
