package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.DisinfectionDao;
import org.infosystema.study_abroad.dao.impl.DisinfectionDaoImpl;
import org.infosystema.study_abroad.model.app.Disinfection;
import org.infosystema.study_abroad.service.DisinfectionService;

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
