package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.SubdivisionDao;
import org.infosystema.iselect.model.Subdivision;

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
