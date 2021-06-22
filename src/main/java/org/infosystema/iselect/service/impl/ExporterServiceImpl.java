package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.ExporterDao;
import org.infosystema.iselect.dao.impl.ExporterDaoImpl;
import org.infosystema.iselect.model.app.Exporters;
import org.infosystema.iselect.service.ExporterService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ExporterService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ExporterServiceImpl extends GenericServiceImpl<Exporters, Integer, ExporterDao> implements ExporterService {

	@Override
	protected ExporterDao getDao() {
		return new ExporterDaoImpl(em, se);
	}

}
