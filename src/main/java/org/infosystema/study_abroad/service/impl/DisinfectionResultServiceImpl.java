package org.infosystema.study_abroad.service.impl;

import org.infosystema.study_abroad.dao.DisinfectionResultDao;
import org.infosystema.study_abroad.dao.impl.DisinfectionResultDaoImpl;
import org.infosystema.study_abroad.model.app.DisinfectionResult;
import org.infosystema.study_abroad.service.DisinfectionResultService;

import javax.ejb.*;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(DisinfectionResultService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DisinfectionResultServiceImpl extends GenericServiceImpl<DisinfectionResult, Integer, DisinfectionResultDao> implements DisinfectionResultService {

	@Override
	protected DisinfectionResultDao getDao() {
		return new DisinfectionResultDaoImpl(em, se);
	}

}
