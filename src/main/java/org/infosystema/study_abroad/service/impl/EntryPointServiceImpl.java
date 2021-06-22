package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.EntryPointDao;
import org.infosystema.study_abroad.dao.impl.EntryPointDaoImpl;
import org.infosystema.study_abroad.model.nomenclature.EntryPoint;
import org.infosystema.study_abroad.service.EntryPointService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(EntryPointService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EntryPointServiceImpl extends GenericServiceImpl<EntryPoint, Integer, EntryPointDao> implements EntryPointService {

	@Override
	protected EntryPointDao getDao() {
		return new EntryPointDaoImpl(em, se);
	}

}
