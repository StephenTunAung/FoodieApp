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

import com.keyit.dto.OtherFacility;

@Service("facilityService")
@Transactional
public class FacilityService {
	// Session factory injected by spring context
	private SessionFactory sessionFactory;

	private static final Logger logger = LoggerFactory
			.getLogger(FacilityService.class);

	public OtherFacility getFacilityByID(Integer id) {

		Session session = this.sessionFactory.getCurrentSession();
		OtherFacility otherFacility = new OtherFacility();

		try {
			otherFacility = (OtherFacility) session.get(OtherFacility.class,
					id);
		} catch (HibernateException he) {

			logger.error(he.getMessage());
		}

		return otherFacility;
	}

	@SuppressWarnings("unchecked")
	public List<OtherFacility> getAllFacilities() {
		Session session = this.sessionFactory.getCurrentSession();
		List<OtherFacility> otherFacilities = Collections.emptyList();

		try {
			otherFacilities = session.createQuery("from OtherFacility").list();
		} catch (HibernateException he) {
			logger.error(he.getMessage());
		}
		return otherFacilities;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
