package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ContractSignedDao;
import org.infosystema.study_abroad.dao.impl.ContractSignedDaoImpl;
import org.infosystema.study_abroad.model.docs.ContractSigned;
import org.infosystema.study_abroad.service.ContractSignedService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ContractSignedService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ContractSignedServiceImpl extends GenericServiceImpl<ContractSigned, Integer, ContractSignedDao> implements ContractSignedService {

	@Override
	protected ContractSignedDao getDao() {
		return new ContractSignedDaoImpl(em, se);
	}

}
