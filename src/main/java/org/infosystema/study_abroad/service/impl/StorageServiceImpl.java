package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.StorageDao;
import org.infosystema.study_abroad.dao.impl.StorageDaoImpl;
import org.infosystema.study_abroad.model.app.Storage;
import org.infosystema.study_abroad.service.StorageService;

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
