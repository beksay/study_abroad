package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.DisinfectionDao;
import org.infosystema.iselect.dao.impl.DisinfectionDaoImpl;
import org.infosystema.iselect.model.app.Disinfection;
import org.infosystema.iselect.service.DisinfectionService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(DisinfectionService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DisinfectionServiceImpl extends GenericServiceImpl<Disinfection, Integer, DisinfectionDao> implements DisinfectionService {

	@Override
	protected DisinfectionDao getDao() {
		return new DisinfectionDaoImpl(em, se);
	}

}
