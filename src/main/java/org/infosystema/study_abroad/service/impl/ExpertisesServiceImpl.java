package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ExpertisesDao;
import org.infosystema.study_abroad.dao.impl.ExpertisesDaoImpl;
import org.infosystema.study_abroad.model.app.Expertises;
import org.infosystema.study_abroad.service.ExpertisesService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ExpertisesService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ExpertisesServiceImpl extends GenericServiceImpl<Expertises, Integer, ExpertisesDao> implements ExpertisesService {

	@Override
	protected ExpertisesDao getDao() {
		return new ExpertisesDaoImpl(em, se);
	}

}
