package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.UserLogDao;
import org.infosystema.study_abroad.model.UserLog;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class UserLogDaoImpl extends GenericDaoImpl<UserLog, Integer> implements UserLogDao {

	public UserLogDaoImpl(EntityManager entityManager, Event<UserLog> eventSource) {
		super(entityManager, eventSource);
	}
}
