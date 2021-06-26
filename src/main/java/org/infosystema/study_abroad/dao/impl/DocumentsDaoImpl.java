package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.DocumentsDao;
import org.infosystema.study_abroad.model.docs.Documents;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class DocumentsDaoImpl extends GenericDaoImpl<Documents, Integer> implements DocumentsDao {

	public DocumentsDaoImpl(EntityManager entityManager,Event<Documents> eventSource) {
		super(entityManager,eventSource);
	}
}
