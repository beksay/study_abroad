package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ModuleDao;
import org.infosystema.study_abroad.model.docs.Module;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ModuleDaoImpl extends GenericDaoImpl<Module, Integer> implements ModuleDao {

	public ModuleDaoImpl(EntityManager entityManager,Event<Module> eventSource) {
		super(entityManager,eventSource);
	}
}
