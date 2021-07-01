package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ApplicationFeeDao;
import org.infosystema.study_abroad.dao.impl.ApplicationFeeDaoImpl;
import org.infosystema.study_abroad.model.docs.ApplicationFee;
import org.infosystema.study_abroad.service.ApplicationFeeService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ApplicationFeeService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ApplicationFeeServiceImpl extends GenericServiceImpl<ApplicationFee, Integer, ApplicationFeeDao> implements ApplicationFeeService {

	@Override
	protected ApplicationFeeDao getDao() {
		return new ApplicationFeeDaoImpl(em, se);
	}

}
