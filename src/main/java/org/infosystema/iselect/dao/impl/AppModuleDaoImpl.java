package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.AppModuleDao;
import org.infosystema.iselect.model.app.AppModule;

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
