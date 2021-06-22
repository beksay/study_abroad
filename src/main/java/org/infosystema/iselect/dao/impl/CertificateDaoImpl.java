package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.CertificateDao;
import org.infosystema.iselect.model.app.Certificate;

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
