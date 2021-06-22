package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.SubdivisionDao;
import org.infosystema.study_abroad.model.Subdivision;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class SubdivisionDaoImpl extends GenericDaoImpl<Subdivision, Integer> implements SubdivisionDao {

	public SubdivisionDaoImpl(EntityManager entityManager,Event<Subdivision> eventSource) {
		super(entityManager,eventSource);
	}
}
