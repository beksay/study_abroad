package org.infosystema.study_abroad.dao.impl;

import org.infosystema.study_abroad.dao.DisinfectionResultDao;
import org.infosystema.study_abroad.model.app.DisinfectionResult;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;


public class DisinfectionResultDaoImpl extends GenericDaoImpl<DisinfectionResult, Integer> implements DisinfectionResultDao {

	public DisinfectionResultDaoImpl(EntityManager entityManager, Event<DisinfectionResult> eventSource) {
		super(entityManager,eventSource);
	}
}
