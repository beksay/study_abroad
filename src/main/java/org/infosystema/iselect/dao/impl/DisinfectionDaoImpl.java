package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.DisinfectionDao;
import org.infosystema.iselect.model.app.Disinfection;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class DisinfectionDaoImpl extends GenericDaoImpl<Disinfection, Integer> implements DisinfectionDao {

	public DisinfectionDaoImpl(EntityManager entityManager,Event<Disinfection> eventSource) {
		super(entityManager,eventSource);
	}
}
