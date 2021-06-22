package org.infosystema.study_abroad.dao.impl;

import org.infosystema.study_abroad.dao.ExpertisesResultDao;
import org.infosystema.study_abroad.model.app.ExpertisesResult;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;


public class ExpertisesResultDaoImpl extends GenericDaoImpl<ExpertisesResult, Integer> implements ExpertisesResultDao {

	public ExpertisesResultDaoImpl(EntityManager entityManager, Event<ExpertisesResult> eventSource) {
		super(entityManager,eventSource);
	}
}
