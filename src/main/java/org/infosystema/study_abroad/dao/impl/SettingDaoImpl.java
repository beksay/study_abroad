package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.SettingDao;
import org.infosystema.study_abroad.model.Setting;

public class SettingDaoImpl extends GenericDaoImpl<Setting, Integer> implements SettingDao {

	public SettingDaoImpl(EntityManager entityManager,Event<Setting> eventSource) {
		super(entityManager,eventSource);
	}
}
