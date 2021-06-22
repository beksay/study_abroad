package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.OrganizationViolationDao;
import org.infosystema.study_abroad.dao.impl.OrganizationViolationDaoImpl;
import org.infosystema.study_abroad.model.nomenclature.OrganizationViolation;
import org.infosystema.study_abroad.service.OrganizationViolationService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(OrganizationViolationService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OrganizationViolationServiceImpl extends GenericServiceImpl<OrganizationViolation, Integer, OrganizationViolationDao> implements OrganizationViolationService {

	@Override
	protected OrganizationViolationDao getDao() {
		return new OrganizationViolationDaoImpl(em, se);
	}

}
