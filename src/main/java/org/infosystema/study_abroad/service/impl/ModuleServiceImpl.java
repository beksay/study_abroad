package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ModuleDao;
import org.infosystema.study_abroad.dao.impl.ModuleDaoImpl;
import org.infosystema.study_abroad.model.docs.Module;
import org.infosystema.study_abroad.service.ModuleService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ModuleService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ModuleServiceImpl extends GenericServiceImpl<Module, Integer, ModuleDao> implements ModuleService {

	@Override
	protected ModuleDao getDao() {
		return new ModuleDaoImpl(em, se);
	}

}
