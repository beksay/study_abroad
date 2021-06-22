package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.LoginLogDao;
import org.infosystema.iselect.model.LoginLog;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class LoginLogDaoImpl extends GenericDaoImpl<LoginLog, Integer> implements LoginLogDao {

	public LoginLogDaoImpl(EntityManager entityManager, Event<LoginLog> eventSource) {
		super(entityManager, eventSource);
	}

}
