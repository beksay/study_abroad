package org.infosystema.iselect.dao.impl;

import org.infosystema.iselect.dao.ExpertisesResultDao;
import org.infosystema.iselect.model.app.ExpertisesResult;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;


public class ExpertisesResultDaoImpl extends GenericDaoImpl<ExpertisesResult, Integer> implements ExpertisesResultDao {

	public ExpertisesResultDaoImpl(EntityManager entityManager, Event<ExpertisesResult> eventSource) {
		super(entityManager,eventSource);
	}
}
