package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.NoStorageDao;
import org.infosystema.study_abroad.dao.impl.NoStorageDaoImpl;
import org.infosystema.study_abroad.model.app.NoStorage;
import org.infosystema.study_abroad.service.NoStorageService;

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
