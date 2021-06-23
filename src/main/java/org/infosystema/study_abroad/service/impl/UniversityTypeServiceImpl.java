package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.UniversityTypeDao;
import org.infosystema.study_abroad.dao.impl.UniversityTypeDaoImpl;
import org.infosystema.study_abroad.model.UniversityType;
import org.infosystema.study_abroad.service.UniversityTypeService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(UniversityTypeService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UniversityTypeServiceImpl extends GenericServiceImpl<UniversityType, Integer, UniversityTypeDao> implements UniversityTypeService {

	@Override
	protected UniversityTypeDao getDao() {
		return new UniversityTypeDaoImpl(em, se);
	}

}
