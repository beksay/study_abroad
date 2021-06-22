package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.CertificateDao;
import org.infosystema.study_abroad.model.app.Certificate;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class CertificateDaoImpl extends GenericDaoImpl<Certificate, Integer> implements CertificateDao {

	public CertificateDaoImpl(EntityManager entityManager,Event<Certificate> eventSource) {
		super(entityManager,eventSource);
	}
}
