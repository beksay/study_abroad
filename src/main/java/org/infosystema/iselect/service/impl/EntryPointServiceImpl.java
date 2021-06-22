package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.EntryPointDao;
import org.infosystema.iselect.dao.impl.EntryPointDaoImpl;
import org.infosystema.iselect.model.nomenclature.EntryPoint;
import org.infosystema.iselect.service.EntryPointService;

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
