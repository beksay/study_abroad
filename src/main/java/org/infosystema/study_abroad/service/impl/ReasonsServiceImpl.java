package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ReasonsDao;
import org.infosystema.study_abroad.dao.impl.ReasonsDaoImpl;
import org.infosystema.study_abroad.model.docs.Reasons;
import org.infosystema.study_abroad.service.ReasonsService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ReasonsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ReasonsServiceImpl extends GenericServiceImpl<Reasons, Integer, ReasonsDao> implements ReasonsService {

	@Override
	protected ReasonsDao getDao() {
		return new ReasonsDaoImpl(em, se);
	}

}
