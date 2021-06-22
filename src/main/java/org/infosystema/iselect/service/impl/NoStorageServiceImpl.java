package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.NoStorageDao;
import org.infosystema.iselect.dao.impl.NoStorageDaoImpl;
import org.infosystema.iselect.model.app.NoStorage;
import org.infosystema.iselect.service.NoStorageService;

@Stateless
@Local(NoStorageService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class NoStorageServiceImpl extends GenericServiceImpl<NoStorage, Integer, NoStorageDao>
		implements NoStorageService {

	@Override
	protected NoStorageDao getDao() {
		return new NoStorageDaoImpl(em, se);
	}

}
