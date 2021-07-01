package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.VisaDao;
import org.infosystema.study_abroad.dao.impl.VisaDaoImpl;
import org.infosystema.study_abroad.model.docs.Visa;
import org.infosystema.study_abroad.service.VisaService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(VisaService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VisaServiceImpl extends GenericServiceImpl<Visa, Integer, VisaDao> implements VisaService {

	@Override
	protected VisaDao getDao() {
		return new VisaDaoImpl(em, se);
	}

}
