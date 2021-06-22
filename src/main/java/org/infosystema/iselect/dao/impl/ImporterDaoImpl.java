package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.ImporterDao;
import org.infosystema.iselect.model.app.Importers;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ImporterDaoImpl extends GenericDaoImpl<Importers, Integer> implements ImporterDao {

	public ImporterDaoImpl(EntityManager entityManager,Event<Importers> eventSource) {
		super(entityManager,eventSource);
	}
}
