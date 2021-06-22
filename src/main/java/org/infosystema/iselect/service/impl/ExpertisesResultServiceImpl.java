package org.infosystema.iselect.service.impl;

import org.infosystema.iselect.dao.ExpertisesResultDao;
import org.infosystema.iselect.dao.impl.ExpertisesResultDaoImpl;
import org.infosystema.iselect.model.app.ExpertisesResult;
import org.infosystema.iselect.service.ExpertisesResultService;

import javax.ejb.*;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ExpertisesResultService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ExpertisesResultServiceImpl extends GenericServiceImpl<ExpertisesResult, Integer, ExpertisesResultDao> implements ExpertisesResultService {

	@Override
	protected ExpertisesResultDao getDao() {
		return new ExpertisesResultDaoImpl(em, se);
	}

}
