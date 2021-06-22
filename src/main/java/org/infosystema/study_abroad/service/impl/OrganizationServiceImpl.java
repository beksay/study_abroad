package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.OrganizationDao;
import org.infosystema.study_abroad.dao.impl.OrganizationDaoImpl;
import org.infosystema.study_abroad.model.nomenclature.Organization;
import org.infosystema.study_abroad.service.OrganizationService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(OrganizationService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class OrganizationServiceImpl extends GenericServiceImpl<Organization, Integer, OrganizationDao> implements OrganizationService {

	@Override
	protected OrganizationDao getDao() {
		return new OrganizationDaoImpl(em, se);
	}

}
