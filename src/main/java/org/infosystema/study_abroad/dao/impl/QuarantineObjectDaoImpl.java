package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.QuarantineObjectDao;
import org.infosystema.study_abroad.model.nomenclature.QuarantineObject;

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
