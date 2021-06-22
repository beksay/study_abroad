package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.ViolationOfLawDao;
import org.infosystema.iselect.dao.impl.ViolationOfLawDaoImpl;
import org.infosystema.iselect.model.nomenclature.ViolationOfLaw;
import org.infosystema.iselect.service.ViolationOfLawService;

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
