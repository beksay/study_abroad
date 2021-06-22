package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.DocumentsStepDao;
import org.infosystema.iselect.model.app.DocumentsStep;

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
