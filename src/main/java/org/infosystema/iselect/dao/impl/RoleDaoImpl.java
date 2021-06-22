package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.RoleDao;
import org.infosystema.iselect.model.Role;

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
