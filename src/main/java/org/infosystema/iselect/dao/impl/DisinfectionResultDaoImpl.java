package org.infosystema.iselect.dao.impl;

import org.infosystema.iselect.dao.DisinfectionResultDao;
import org.infosystema.iselect.model.app.DisinfectionResult;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;


public class DisinfectionResultDaoImpl extends GenericDaoImpl<DisinfectionResult, Integer> implements DisinfectionResultDao {

	public DisinfectionResultDaoImpl(EntityManager entityManager, Event<DisinfectionResult> eventSource) {
		super(entityManager,eventSource);
	}
}
