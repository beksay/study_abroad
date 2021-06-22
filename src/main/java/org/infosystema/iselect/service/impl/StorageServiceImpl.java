package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.StorageDao;
import org.infosystema.iselect.dao.impl.StorageDaoImpl;
import org.infosystema.iselect.model.app.Storage;
import org.infosystema.iselect.service.StorageService;

@Stateless
@Local(StorageService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class StorageServiceImpl extends GenericServiceImpl<Storage, Integer, StorageDao>
		implements StorageService {

	@Override
	protected StorageDao getDao() {
		return new StorageDaoImpl(em, se);
	}

}
