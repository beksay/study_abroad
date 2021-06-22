package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.StorageDao;
import org.infosystema.study_abroad.model.app.Storage;

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
