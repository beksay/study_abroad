package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.VisaDocumentDao;
import org.infosystema.study_abroad.model.docs.VisaDocument;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class VisaDocumentDaoImpl extends GenericDaoImpl<VisaDocument, Integer> implements VisaDocumentDao {

	public VisaDocumentDaoImpl(EntityManager entityManager,Event<VisaDocument> eventSource) {
		super(entityManager,eventSource);
	}
}
