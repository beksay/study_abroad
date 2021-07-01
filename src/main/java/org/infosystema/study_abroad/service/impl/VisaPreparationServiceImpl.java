package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.VisaPreparationDao;
import org.infosystema.study_abroad.dao.impl.VisaPreparationDaoImpl;
import org.infosystema.study_abroad.model.docs.VisaPreparation;
import org.infosystema.study_abroad.service.VisaPreparationService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(VisaPreparationService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VisaPreparationServiceImpl extends GenericServiceImpl<VisaPreparation, Integer, VisaPreparationDao> implements VisaPreparationService {

	@Override
	protected VisaPreparationDao getDao() {
		return new VisaPreparationDaoImpl(em, se);
	}

}
