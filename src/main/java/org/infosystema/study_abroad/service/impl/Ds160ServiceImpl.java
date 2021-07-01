package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.Ds160Dao;
import org.infosystema.study_abroad.dao.impl.Ds160DaoImpl;
import org.infosystema.study_abroad.model.docs.Ds160;
import org.infosystema.study_abroad.service.Ds160Service;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(Ds160Service.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class Ds160ServiceImpl extends GenericServiceImpl<Ds160, Integer, Ds160Dao> implements Ds160Service {

	@Override
	protected Ds160Dao getDao() {
		return new Ds160DaoImpl(em, se);
	}

}
