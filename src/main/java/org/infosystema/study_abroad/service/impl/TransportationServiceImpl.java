package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.TransportationDao;
import org.infosystema.study_abroad.dao.impl.TransportationDaoImpl;
import org.infosystema.study_abroad.model.app.Transportations;
import org.infosystema.study_abroad.service.TransportationService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(TransportationService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class TransportationServiceImpl extends GenericServiceImpl<Transportations, Integer, TransportationDao> implements TransportationService {

	@Override
	protected TransportationDao getDao() {
		return new TransportationDaoImpl(em, se);
	}

}
