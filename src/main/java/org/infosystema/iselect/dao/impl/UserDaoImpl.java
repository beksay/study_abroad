package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.UserDao;
import org.infosystema.iselect.model.User;

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
