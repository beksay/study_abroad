package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.MapZoneDao;
import org.infosystema.study_abroad.dao.impl.MapZoneDaoImpl;
import org.infosystema.study_abroad.model.app.MapZone;
import org.infosystema.study_abroad.service.MapZoneService;

@Stateless
@Local(MapZoneService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MapZoneServiceImpl extends GenericServiceImpl<MapZone, Integer, MapZoneDao>
		implements MapZoneService {

	@Override
	protected MapZoneDao getDao() {
		return new MapZoneDaoImpl(em, se);
	}

}
