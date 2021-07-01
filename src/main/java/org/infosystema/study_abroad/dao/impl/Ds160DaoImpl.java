package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.Ds160Dao;
import org.infosystema.study_abroad.model.docs.Ds160;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class Ds160DaoImpl extends GenericDaoImpl<Ds160, Integer> implements Ds160Dao {

	public Ds160DaoImpl(EntityManager entityManager,Event<Ds160> eventSource) {
		super(entityManager,eventSource);
	}
}
