package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.VisaDao;
import org.infosystema.study_abroad.model.docs.Visa;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class VisaDaoImpl extends GenericDaoImpl<Visa, Integer> implements VisaDao {

	public VisaDaoImpl(EntityManager entityManager,Event<Visa> eventSource) {
		super(entityManager,eventSource);
	}
}
