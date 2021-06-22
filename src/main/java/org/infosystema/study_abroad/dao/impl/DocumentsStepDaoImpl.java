package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.DocumentsStepDao;
import org.infosystema.study_abroad.model.app.DocumentsStep;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class DocumentsStepDaoImpl extends GenericDaoImpl<DocumentsStep, Integer> implements DocumentsStepDao {

	public DocumentsStepDaoImpl(EntityManager entityManager,Event<DocumentsStep> eventSource) {
		super(entityManager,eventSource);
	}
}
