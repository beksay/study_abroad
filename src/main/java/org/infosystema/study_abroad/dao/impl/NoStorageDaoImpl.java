package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.NoStorageDao;
import org.infosystema.study_abroad.model.app.NoStorage;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class NoStorageDaoImpl extends GenericDaoImpl<NoStorage, Integer> implements NoStorageDao {

	public NoStorageDaoImpl(EntityManager entityManager, Event<NoStorage> eventSource) {
		super(entityManager, eventSource);
	}
}
