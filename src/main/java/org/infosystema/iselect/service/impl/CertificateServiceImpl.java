package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.CertificateDao;
import org.infosystema.iselect.dao.impl.CertificateDaoImpl;
import org.infosystema.iselect.model.app.Certificate;
import org.infosystema.iselect.service.CertificateService;

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
