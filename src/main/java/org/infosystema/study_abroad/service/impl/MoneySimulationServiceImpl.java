package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.MoneySimulationDao;
import org.infosystema.study_abroad.dao.impl.MoneySimulationDaoImpl;
import org.infosystema.study_abroad.model.docs.MoneySimulation;
import org.infosystema.study_abroad.service.MoneySimulationService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(MoneySimulationService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MoneySimulationServiceImpl extends GenericServiceImpl<MoneySimulation, Integer, MoneySimulationDao> implements MoneySimulationService {

	@Override
	protected MoneySimulationDao getDao() {
		return new MoneySimulationDaoImpl(em, se);
	}

}
