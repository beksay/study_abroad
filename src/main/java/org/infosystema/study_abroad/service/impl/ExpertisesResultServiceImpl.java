package org.infosystema.study_abroad.service.impl;

import org.infosystema.study_abroad.dao.ExpertisesResultDao;
import org.infosystema.study_abroad.dao.impl.ExpertisesResultDaoImpl;
import org.infosystema.study_abroad.model.app.ExpertisesResult;
import org.infosystema.study_abroad.service.ExpertisesResultService;

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
