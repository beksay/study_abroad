package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ViolationOfLawDao;
import org.infosystema.study_abroad.dao.impl.ViolationOfLawDaoImpl;
import org.infosystema.study_abroad.model.nomenclature.ViolationOfLaw;
import org.infosystema.study_abroad.service.ViolationOfLawService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ViolationOfLawService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ViolationOfLawServiceImpl extends GenericServiceImpl<ViolationOfLaw, Integer, ViolationOfLawDao> implements ViolationOfLawService {

	@Override
	protected ViolationOfLawDao getDao() {
		return new ViolationOfLawDaoImpl(em, se);
	}

}
