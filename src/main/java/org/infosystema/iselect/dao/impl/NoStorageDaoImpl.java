package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.NoStorageDao;
import org.infosystema.iselect.model.app.NoStorage;

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
