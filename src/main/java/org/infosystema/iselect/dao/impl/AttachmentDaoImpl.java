package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.AttachmentDao;
import org.infosystema.iselect.model.Attachment;

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
