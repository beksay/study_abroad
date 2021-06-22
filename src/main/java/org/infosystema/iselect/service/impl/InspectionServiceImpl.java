package org.infosystema.iselect.service.impl;

import org.infosystema.iselect.dao.InspectionDao;
import org.infosystema.iselect.dao.impl.InspectionDaoImpl;
import org.infosystema.iselect.model.app.Inspection;
import org.infosystema.iselect.service.InspectionService;

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
