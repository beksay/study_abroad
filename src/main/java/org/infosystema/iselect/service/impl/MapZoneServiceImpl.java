package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.MapZoneDao;
import org.infosystema.iselect.dao.impl.MapZoneDaoImpl;
import org.infosystema.iselect.model.app.MapZone;
import org.infosystema.iselect.service.MapZoneService;

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
