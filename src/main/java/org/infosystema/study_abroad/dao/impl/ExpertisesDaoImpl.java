package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ExpertisesDao;
import org.infosystema.study_abroad.model.app.Expertises;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ExpertisesDaoImpl extends GenericDaoImpl<Expertises, Integer> implements ExpertisesDao {

	public ExpertisesDaoImpl(EntityManager entityManager,Event<Expertises> eventSource) {
		super(entityManager,eventSource);
	}
}
