package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.DictionaryDao;
import org.infosystema.study_abroad.dao.impl.DictionaryDaoImpl;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.service.DictionaryService;

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
