package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.ApplicationsDao;
import org.infosystema.iselect.model.app.Applications;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ApplicationsDaoImpl extends GenericDaoImpl<Applications, Integer> implements ApplicationsDao {

	public ApplicationsDaoImpl(EntityManager entityManager,Event<Applications> eventSource) {
		super(entityManager,eventSource);
	}
}
