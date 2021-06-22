package org.infosystema.iselect.service.impl;

import org.infosystema.iselect.dao.DisinfectionResultDao;
import org.infosystema.iselect.dao.impl.DisinfectionResultDaoImpl;
import org.infosystema.iselect.model.app.DisinfectionResult;
import org.infosystema.iselect.service.DisinfectionResultService;

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
