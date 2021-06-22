package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.StorageDao;
import org.infosystema.iselect.model.app.Storage;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class StorageDaoImpl extends GenericDaoImpl<Storage, Integer> implements StorageDao {

	public StorageDaoImpl(EntityManager entityManager, Event<Storage> eventSource) {
		super(entityManager, eventSource);
	}
}
