package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.DocumentsStepDao;
import org.infosystema.iselect.dao.impl.DocumentsStepDaoImpl;
import org.infosystema.iselect.model.app.DocumentsStep;
import org.infosystema.iselect.service.DocumentsStepService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(DocumentsStepService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class DocumentsStepServiceImpl extends GenericServiceImpl<DocumentsStep, Integer, DocumentsStepDao> implements DocumentsStepService {

	@Override
	protected DocumentsStepDao getDao() {
		return new DocumentsStepDaoImpl(em, se);
	}

}
