package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.ExporterDao;
import org.infosystema.iselect.model.app.Exporters;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ExporterDaoImpl extends GenericDaoImpl<Exporters, Integer> implements ExporterDao {

	public ExporterDaoImpl(EntityManager entityManager,Event<Exporters> eventSource) {
		super(entityManager,eventSource);
	}
}
