package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.QuarantineObjectDao;
import org.infosystema.iselect.dao.impl.QuarantineObjectDaoImpl;
import org.infosystema.iselect.model.nomenclature.QuarantineObject;
import org.infosystema.iselect.service.QuarantineObjectService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(QuarantineObjectService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class QuarantineObjectServiceImpl extends GenericServiceImpl<QuarantineObject, Integer, QuarantineObjectDao> implements QuarantineObjectService {

	@Override
	protected QuarantineObjectDao getDao() {
		return new QuarantineObjectDaoImpl(em, se);
	}

}
