package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ITwentyDao;
import org.infosystema.study_abroad.dao.impl.ITwentyDaoImpl;
import org.infosystema.study_abroad.model.docs.ITwenty;
import org.infosystema.study_abroad.service.ITwentyService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ITwentyService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ITwentyServiceImpl extends GenericServiceImpl<ITwenty, Integer, ITwentyDao> implements ITwentyService {

	@Override
	protected ITwentyDao getDao() {
		return new ITwentyDaoImpl(em, se);
	}

}
