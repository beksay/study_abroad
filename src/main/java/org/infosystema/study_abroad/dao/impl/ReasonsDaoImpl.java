package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ReasonsDao;
import org.infosystema.study_abroad.model.docs.Reasons;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ReasonsDaoImpl extends GenericDaoImpl<Reasons, Integer> implements ReasonsDao {

	public ReasonsDaoImpl(EntityManager entityManager,Event<Reasons> eventSource) {
		super(entityManager,eventSource);
	}
}
