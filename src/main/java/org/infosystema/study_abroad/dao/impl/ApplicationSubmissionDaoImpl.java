package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ApplicationSubmissionDao;
import org.infosystema.study_abroad.model.docs.ApplicationSubmission;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ApplicationSubmissionDaoImpl extends GenericDaoImpl<ApplicationSubmission, Integer> implements ApplicationSubmissionDao {

	public ApplicationSubmissionDaoImpl(EntityManager entityManager,Event<ApplicationSubmission> eventSource) {
		super(entityManager,eventSource);
	}
}
