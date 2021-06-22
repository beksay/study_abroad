package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.LoginLogDao;
import org.infosystema.study_abroad.dao.impl.LoginLogDaoImpl;
import org.infosystema.study_abroad.model.LoginLog;
import org.infosystema.study_abroad.service.LoginLogService;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Stateless
@Local(LoginLogService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class LoginLogServiceImpl extends GenericServiceImpl<LoginLog, Integer, LoginLogDao> implements LoginLogService {

	@Override
	protected LoginLogDao getDao() {
		return new LoginLogDaoImpl(em, se);
	}

}
