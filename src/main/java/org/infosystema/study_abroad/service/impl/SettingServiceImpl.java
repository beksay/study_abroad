package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.SettingDao;
import org.infosystema.study_abroad.dao.impl.SettingDaoImpl;
import org.infosystema.study_abroad.model.Setting;
import org.infosystema.study_abroad.service.SettingService;

@Stateless
@Local(SettingService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class SettingServiceImpl extends GenericServiceImpl<Setting, Integer, SettingDao> implements SettingService {

	@Override
	protected SettingDao getDao() {
		return new SettingDaoImpl(em, se);
	}

}
