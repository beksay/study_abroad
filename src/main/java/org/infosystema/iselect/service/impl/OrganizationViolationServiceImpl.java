package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.OrganizationViolationDao;
import org.infosystema.iselect.dao.impl.OrganizationViolationDaoImpl;
import org.infosystema.iselect.model.nomenclature.OrganizationViolation;
import org.infosystema.iselect.service.OrganizationViolationService;

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
