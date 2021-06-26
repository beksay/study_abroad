package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ApplicationsDao;
import org.infosystema.study_abroad.model.docs.Applications;

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
