package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.CertificateDao;
import org.infosystema.study_abroad.dao.impl.CertificateDaoImpl;
import org.infosystema.study_abroad.model.app.Certificate;
import org.infosystema.study_abroad.service.CertificateService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(CertificateService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class CertificateServiceImpl extends GenericServiceImpl<Certificate, Integer, CertificateDao> implements CertificateService {

	@Override
	protected CertificateDao getDao() {
		return new CertificateDaoImpl(em, se);
	}

}
