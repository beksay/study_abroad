package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.ImporterDao;
import org.infosystema.iselect.dao.impl.ImporterDaoImpl;
import org.infosystema.iselect.model.app.Importers;
import org.infosystema.iselect.service.ImporterService;

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
