package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.TransportTypeDao;
import org.infosystema.study_abroad.model.nomenclature.TransportType;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class TransportTypeDaoImpl extends GenericDaoImpl<TransportType, Integer> implements TransportTypeDao {

	public TransportTypeDaoImpl(EntityManager entityManager,Event<TransportType> eventSource) {
		super(entityManager,eventSource);
	}
}
