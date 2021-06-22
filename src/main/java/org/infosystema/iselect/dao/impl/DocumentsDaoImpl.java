package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.DocumentsDao;
import org.infosystema.iselect.model.app.Documents;

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
