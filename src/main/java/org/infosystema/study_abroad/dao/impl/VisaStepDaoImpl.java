package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.VisaStepDao;
import org.infosystema.study_abroad.model.docs.VisaStep;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class VisaStepDaoImpl extends GenericDaoImpl<VisaStep, Integer> implements VisaStepDao {

	public VisaStepDaoImpl(EntityManager entityManager,Event<VisaStep> eventSource) {
		super(entityManager,eventSource);
	}
}
