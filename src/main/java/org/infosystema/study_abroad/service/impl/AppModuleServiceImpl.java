package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.AppModuleDao;
import org.infosystema.study_abroad.dao.impl.AppModuleDaoImpl;
import org.infosystema.study_abroad.model.app.AppModule;
import org.infosystema.study_abroad.service.AppModuleService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(AppModuleService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class AppModuleServiceImpl extends GenericServiceImpl<AppModule, Integer, AppModuleDao> implements AppModuleService {

	@Override
	protected AppModuleDao getDao() {
		return new AppModuleDaoImpl(em, se);
	}

}
