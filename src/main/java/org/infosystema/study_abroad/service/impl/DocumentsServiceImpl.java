package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.DocumentsDao;
import org.infosystema.study_abroad.dao.impl.DocumentsDaoImpl;
import org.infosystema.study_abroad.model.app.Documents;
import org.infosystema.study_abroad.service.DocumentsService;

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
