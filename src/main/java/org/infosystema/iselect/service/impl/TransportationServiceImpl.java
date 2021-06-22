package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.TransportationDao;
import org.infosystema.iselect.dao.impl.TransportationDaoImpl;
import org.infosystema.iselect.model.app.Transportations;
import org.infosystema.iselect.service.TransportationService;

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
