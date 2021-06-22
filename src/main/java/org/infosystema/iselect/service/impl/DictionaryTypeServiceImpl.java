package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.DictionaryTypeDao;
import org.infosystema.iselect.dao.impl.DictionaryTypeDaoImpl;
import org.infosystema.iselect.model.DictionaryType;
import org.infosystema.iselect.service.DictionaryTypeService;

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
