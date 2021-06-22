package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.DictionaryDao;
import org.infosystema.iselect.dao.impl.DictionaryDaoImpl;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.service.DictionaryService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(DictionaryService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DictionaryServiceImpl extends GenericServiceImpl<Dictionary, Integer, DictionaryDao> implements DictionaryService {

	@Override
	protected DictionaryDao getDao() {
		return new DictionaryDaoImpl(em, se);
	}

}
