package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.RoleDao;
import org.infosystema.iselect.dao.impl.RoleDaoImpl;
import org.infosystema.iselect.model.Role;
import org.infosystema.iselect.service.RoleService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(RoleService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class RoleServiceImpl extends GenericServiceImpl<Role, Integer, RoleDao> implements RoleService {

	@Override
	protected RoleDao getDao() {
		return new RoleDaoImpl(em, se);
	}

}
