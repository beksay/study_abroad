package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ITwentyDao;
import org.infosystema.study_abroad.model.docs.ITwenty;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ITwentyDaoImpl extends GenericDaoImpl<ITwenty, Integer> implements ITwentyDao {

	public ITwentyDaoImpl(EntityManager entityManager,Event<ITwenty> eventSource) {
		super(entityManager,eventSource);
	}
}
