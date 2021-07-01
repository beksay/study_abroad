package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.VisaStepDao;
import org.infosystema.study_abroad.dao.impl.VisaStepDaoImpl;
import org.infosystema.study_abroad.model.docs.VisaStep;
import org.infosystema.study_abroad.service.VisaStepService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(VisaStepService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VisaStepServiceImpl extends GenericServiceImpl<VisaStep, Integer, VisaStepDao> implements VisaStepService {

	@Override
	protected VisaStepDao getDao() {
		return new VisaStepDaoImpl(em, se);
	}

}
