package org.infosystema.iselect.dao.impl;

import org.infosystema.iselect.dao.InspectionDao;
import org.infosystema.iselect.model.app.Inspection;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;


public class InspectionDaoImpl extends GenericDaoImpl<Inspection, Integer> implements InspectionDao {

	public InspectionDaoImpl(EntityManager entityManager, Event<Inspection> eventSource) {
		super(entityManager,eventSource);
	}
}
