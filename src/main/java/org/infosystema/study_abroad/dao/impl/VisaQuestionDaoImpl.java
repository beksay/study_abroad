package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.VisaQuestionDao;
import org.infosystema.study_abroad.model.docs.VisaQuestion;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class VisaQuestionDaoImpl extends GenericDaoImpl<VisaQuestion, Integer> implements VisaQuestionDao {

	public VisaQuestionDaoImpl(EntityManager entityManager,Event<VisaQuestion> eventSource) {
		super(entityManager,eventSource);
	}
}
