package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.DocumentsDao;
import org.infosystema.iselect.dao.impl.DocumentsDaoImpl;
import org.infosystema.iselect.model.app.Documents;
import org.infosystema.iselect.service.DocumentsService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(DocumentsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DocumentsServiceImpl extends GenericServiceImpl<Documents, Integer, DocumentsDao> implements DocumentsService {

	@Override
	protected DocumentsDao getDao() {
		return new DocumentsDaoImpl(em, se);
	}

}
