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

import com.keyit.dto.DressCode;

@Service("dressCodeService")
@Transactional
public class DressCodeService {

	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(FacilityService.class);

	public DressCode getDressCodeById(Integer id) {

		Session session = this.sessionFactory.getCurrentSession();
		DressCode dressCode = new DressCode();

		try {
			dressCode = (DressCode) session.get(DressCode.class, id);
		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}

		return dressCode;
	}

	@SuppressWarnings("unchecked")
	public List<DressCode> getAllDressCodes() {
		Session session = this.sessionFactory.getCurrentSession();
		List<DressCode> dressCodes = Collections.emptyList();

		try {
			dressCodes = session.createQuery("from DressCode").list();
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
		return dressCodes;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
