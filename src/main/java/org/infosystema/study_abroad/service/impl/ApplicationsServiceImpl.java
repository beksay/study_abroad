package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ApplicationsDao;
import org.infosystema.study_abroad.dao.impl.ApplicationsDaoImpl;
import org.infosystema.study_abroad.model.docs.Applications;
import org.infosystema.study_abroad.service.ApplicationsService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ApplicationsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ApplicationsServiceImpl extends GenericServiceImpl<Applications, Integer, ApplicationsDao> implements ApplicationsService {

	@Override
	protected ApplicationsDao getDao() {
		return new ApplicationsDaoImpl(em, se);
	}

}
