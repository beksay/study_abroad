package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.UniversitiesDao;
import org.infosystema.study_abroad.model.Universities;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class UniversitiesDaoImpl extends GenericDaoImpl<Universities, Integer> implements UniversitiesDao {

	public UniversitiesDaoImpl(EntityManager entityManager,Event<Universities> eventSource) {
		super(entityManager,eventSource);
	}
}
