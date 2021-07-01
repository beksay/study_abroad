package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.DocumentsStepDao;
import org.infosystema.study_abroad.dao.impl.DocumentsStepDaoImpl;
import org.infosystema.study_abroad.model.docs.DocumentsStep;
import org.infosystema.study_abroad.service.DocumentsStepService;

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
