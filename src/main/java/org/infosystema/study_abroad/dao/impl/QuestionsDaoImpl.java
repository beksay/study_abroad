package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.QuestionsDao;
import org.infosystema.study_abroad.model.docs.Questions;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class QuestionsDaoImpl extends GenericDaoImpl<Questions, Integer> implements QuestionsDao {

	public QuestionsDaoImpl(EntityManager entityManager,Event<Questions> eventSource) {
		super(entityManager,eventSource);
	}
}
