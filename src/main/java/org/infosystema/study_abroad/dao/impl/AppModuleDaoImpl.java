package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.AppModuleDao;
import org.infosystema.study_abroad.model.app.AppModule;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class AppModuleDaoImpl extends GenericDaoImpl<AppModule, Integer> implements AppModuleDao {

	public AppModuleDaoImpl(EntityManager entityManager,Event<AppModule> eventSource) {
		super(entityManager,eventSource);
	}
}
