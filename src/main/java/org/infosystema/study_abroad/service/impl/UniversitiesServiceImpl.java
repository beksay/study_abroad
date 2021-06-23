package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.UniversitiesDao;
import org.infosystema.study_abroad.dao.impl.UniversitiesDaoImpl;
import org.infosystema.study_abroad.model.Universities;
import org.infosystema.study_abroad.service.UniversitiesService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(UniversitiesService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UniversitiesServiceImpl extends GenericServiceImpl<Universities, Integer, UniversitiesDao> implements UniversitiesService {

	@Override
	protected UniversitiesDao getDao() {
		return new UniversitiesDaoImpl(em, se);
	}

}
