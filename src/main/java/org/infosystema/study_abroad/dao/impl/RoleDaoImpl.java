package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.RoleDao;
import org.infosystema.study_abroad.model.Role;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class RoleDaoImpl extends GenericDaoImpl<Role, Integer> implements RoleDao {

	public RoleDaoImpl(EntityManager entityManager,Event<Role> eventSource) {
		super(entityManager,eventSource);
	}
}
