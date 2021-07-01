package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.MoneySimulationDao;
import org.infosystema.study_abroad.model.docs.MoneySimulation;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class MoneySimulationDaoImpl extends GenericDaoImpl<MoneySimulation, Integer> implements MoneySimulationDao {

	public MoneySimulationDaoImpl(EntityManager entityManager,Event<MoneySimulation> eventSource) {
		super(entityManager,eventSource);
	}
}
