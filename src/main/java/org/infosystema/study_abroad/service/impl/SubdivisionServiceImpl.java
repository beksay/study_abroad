package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.SubdivisionDao;
import org.infosystema.study_abroad.dao.impl.SubdivisionDaoImpl;
import org.infosystema.study_abroad.model.Subdivision;
import org.infosystema.study_abroad.service.SubdivisionService;

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
