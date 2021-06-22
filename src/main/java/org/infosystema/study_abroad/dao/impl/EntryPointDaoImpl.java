package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.EntryPointDao;
import org.infosystema.study_abroad.model.nomenclature.EntryPoint;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class EntryPointDaoImpl extends GenericDaoImpl<EntryPoint, Integer> implements EntryPointDao {

	public EntryPointDaoImpl(EntityManager entityManager,Event<EntryPoint> eventSource) {
		super(entityManager,eventSource);
	}
}
