package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.UserDao;
import org.infosystema.iselect.dao.impl.UserDaoImpl;
import org.infosystema.iselect.model.User;
import org.infosystema.iselect.service.UserService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(UserService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UserServiceImpl extends GenericServiceImpl<User, Integer, UserDao> implements UserService {

	@Override
	protected UserDao getDao() {
		return new UserDaoImpl(em, se);
	}

}
