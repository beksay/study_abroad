package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.SettingDao;
import org.infosystema.iselect.dao.impl.SettingDaoImpl;
import org.infosystema.iselect.model.Setting;
import org.infosystema.iselect.service.SettingService;

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
