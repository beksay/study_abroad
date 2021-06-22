package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.SubdivisionDao;
import org.infosystema.iselect.dao.impl.SubdivisionDaoImpl;
import org.infosystema.iselect.model.Subdivision;
import org.infosystema.iselect.service.SubdivisionService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(SubdivisionService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class SubdivisionServiceImpl extends GenericServiceImpl<Subdivision, Integer, SubdivisionDao> implements SubdivisionService {

	@Override
	protected SubdivisionDao getDao() {
		return new SubdivisionDaoImpl(em, se);
	}

}
