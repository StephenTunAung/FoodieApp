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

import com.keyit.dto.CuisineType;

@Service("cuisineTypeService")
@Transactional
public class CuisineTypeService {

	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(CuisineTypeService.class);

	public CuisineType getCuisineTypeById(Integer id) {
		Session session = this.sessionFactory.getCurrentSession();
		CuisineType cuisineType = new CuisineType();
		try {
			cuisineType = (CuisineType) session.get(CuisineType.class, id);
		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}
		return cuisineType;
	}

	@SuppressWarnings("unchecked")
	public List<CuisineType> getAllCuisineTypes() {
		Session session = this.sessionFactory.getCurrentSession();
		List<CuisineType> cuisineTypes = Collections.emptyList();
		try {
			cuisineTypes = session.createQuery("from CuisineType").list();
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
		return cuisineTypes;

	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
