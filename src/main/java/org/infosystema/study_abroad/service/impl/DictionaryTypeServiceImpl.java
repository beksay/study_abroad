package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.DictionaryTypeDao;
import org.infosystema.study_abroad.dao.impl.DictionaryTypeDaoImpl;
import org.infosystema.study_abroad.model.DictionaryType;
import org.infosystema.study_abroad.service.DictionaryTypeService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(DictionaryTypeService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DictionaryTypeServiceImpl extends GenericServiceImpl<DictionaryType, Integer, DictionaryTypeDao> implements DictionaryTypeService {

	@Override
	protected DictionaryTypeDao getDao() {
		return new DictionaryTypeDaoImpl(em, se);
	}

}
