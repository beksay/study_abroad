package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.TransportationDao;
import org.infosystema.iselect.model.app.Transportations;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class TransportationDaoImpl extends GenericDaoImpl<Transportations, Integer> implements TransportationDao {

	public TransportationDaoImpl(EntityManager entityManager,Event<Transportations> eventSource) {
		super(entityManager,eventSource);
	}
}
