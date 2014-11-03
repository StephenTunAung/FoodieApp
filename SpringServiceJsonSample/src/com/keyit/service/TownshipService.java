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

import com.keyit.dto.Township;

@Service("townshipService") 
@Transactional
public class TownshipService {

	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(TownshipService.class);

	@SuppressWarnings("unchecked")
	public List<Township> getAllTownships() {
		Session session = this.sessionFactory.getCurrentSession();

		List<Township> townships = Collections.emptyList();
		try {
			townships = session.createQuery("from Township").list();
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
		return townships;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}