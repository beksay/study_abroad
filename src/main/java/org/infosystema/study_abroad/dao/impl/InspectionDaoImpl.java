package org.infosystema.study_abroad.dao.impl;

import org.infosystema.study_abroad.dao.InspectionDao;
import org.infosystema.study_abroad.model.app.Inspection;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;


public class InspectionDaoImpl extends GenericDaoImpl<Inspection, Integer> implements InspectionDao {

	public InspectionDaoImpl(EntityManager entityManager, Event<Inspection> eventSource) {
		super(entityManager,eventSource);
	}
}
