package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.UserLogDao;
import org.infosystema.study_abroad.dao.impl.UserLogDaoImpl;
import org.infosystema.study_abroad.model.UserLog;
import org.infosystema.study_abroad.service.UserLogService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(UserLogService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class UserLogServiceImpl extends GenericServiceImpl<UserLog, Integer, UserLogDao> implements UserLogService {

	@Override
	protected UserLogDao getDao() {
		return new UserLogDaoImpl(em, se);
	}

}
