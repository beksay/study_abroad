package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.SettingDao;
import org.infosystema.iselect.model.Setting;

public class SettingDaoImpl extends GenericDaoImpl<Setting, Integer> implements SettingDao {

	public SettingDaoImpl(EntityManager entityManager,Event<Setting> eventSource) {
		super(entityManager,eventSource);
	}
}
