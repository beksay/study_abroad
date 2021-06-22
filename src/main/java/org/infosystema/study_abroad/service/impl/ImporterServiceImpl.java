package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ImporterDao;
import org.infosystema.study_abroad.dao.impl.ImporterDaoImpl;
import org.infosystema.study_abroad.model.app.Importers;
import org.infosystema.study_abroad.service.ImporterService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ImporterService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ImporterServiceImpl extends GenericServiceImpl<Importers, Integer, ImporterDao> implements ImporterService {

	@Override
	protected ImporterDao getDao() {
		return new ImporterDaoImpl(em, se);
	}

}
