package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.TransportTypeDao;
import org.infosystema.study_abroad.dao.impl.TransportTypeDaoImpl;
import org.infosystema.study_abroad.model.nomenclature.TransportType;
import org.infosystema.study_abroad.service.TransportTypeService;

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
