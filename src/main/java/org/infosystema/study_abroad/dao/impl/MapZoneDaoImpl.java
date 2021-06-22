package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.MapZoneDao;
import org.infosystema.study_abroad.model.app.MapZone;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class MapZoneDaoImpl extends GenericDaoImpl<MapZone, Integer> implements MapZoneDao {

	public MapZoneDaoImpl(EntityManager entityManager, Event<MapZone> eventSource) {
		super(entityManager, eventSource);
	}
}
