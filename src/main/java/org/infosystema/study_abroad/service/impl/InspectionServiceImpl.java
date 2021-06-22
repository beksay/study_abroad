package org.infosystema.study_abroad.service.impl;

import org.infosystema.study_abroad.dao.InspectionDao;
import org.infosystema.study_abroad.dao.impl.InspectionDaoImpl;
import org.infosystema.study_abroad.model.app.Inspection;
import org.infosystema.study_abroad.service.InspectionService;

import javax.ejb.*;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(InspectionService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class InspectionServiceImpl extends GenericServiceImpl<Inspection, Integer, InspectionDao> implements InspectionService {

	@Override
	protected InspectionDao getDao() {
		return new InspectionDaoImpl(em, se);
	}

}
