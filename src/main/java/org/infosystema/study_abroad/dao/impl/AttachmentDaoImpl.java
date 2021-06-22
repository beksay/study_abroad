package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.AttachmentDao;
import org.infosystema.study_abroad.model.Attachment;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class AttachmentDaoImpl extends GenericDaoImpl<Attachment, Integer> implements AttachmentDao {

	public AttachmentDaoImpl(EntityManager entityManager,Event<Attachment> eventSource) {
		super(entityManager,eventSource);
	}
}
