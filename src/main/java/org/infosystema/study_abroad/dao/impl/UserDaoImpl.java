package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.UserDao;
import org.infosystema.study_abroad.model.User;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class UserDaoImpl extends GenericDaoImpl<User, Integer> implements UserDao {

	public UserDaoImpl(EntityManager entityManager,Event<User> eventSource) {
		super(entityManager,eventSource);
	}
}
