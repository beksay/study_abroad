package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.QuarantineObjectDao;
import org.infosystema.iselect.model.nomenclature.QuarantineObject;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class QuarantineObjectDaoImpl extends GenericDaoImpl<QuarantineObject, Integer> implements QuarantineObjectDao {

	public QuarantineObjectDaoImpl(EntityManager entityManager,Event<QuarantineObject> eventSource) {
		super(entityManager,eventSource);
	}
}
