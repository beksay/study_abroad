package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.ExpertisesDao;
import org.infosystema.iselect.dao.impl.ExpertisesDaoImpl;
import org.infosystema.iselect.model.app.Expertises;
import org.infosystema.iselect.service.ExpertisesService;

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
