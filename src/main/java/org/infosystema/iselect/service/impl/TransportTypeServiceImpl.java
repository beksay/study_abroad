package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.TransportTypeDao;
import org.infosystema.iselect.dao.impl.TransportTypeDaoImpl;
import org.infosystema.iselect.model.nomenclature.TransportType;
import org.infosystema.iselect.service.TransportTypeService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(TransportTypeService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TransportTypeServiceImpl extends GenericServiceImpl<TransportType, Integer, TransportTypeDao> implements TransportTypeService {

	@Override
	protected TransportTypeDao getDao() {
		return new TransportTypeDaoImpl(em, se);
	}

}
