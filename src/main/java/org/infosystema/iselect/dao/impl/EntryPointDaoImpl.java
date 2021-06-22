package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.EntryPointDao;
import org.infosystema.iselect.model.nomenclature.EntryPoint;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class EntryPointDaoImpl extends GenericDaoImpl<EntryPoint, Integer> implements EntryPointDao {

	public EntryPointDaoImpl(EntityManager entityManager,Event<EntryPoint> eventSource) {
		super(entityManager,eventSource);
	}
}
