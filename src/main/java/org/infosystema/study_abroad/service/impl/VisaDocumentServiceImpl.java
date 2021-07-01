package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.VisaDocumentDao;
import org.infosystema.study_abroad.dao.impl.VisaDocumentDaoImpl;
import org.infosystema.study_abroad.model.docs.VisaDocument;
import org.infosystema.study_abroad.service.VisaDocumentService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(VisaDocumentService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VisaDocumentServiceImpl extends GenericServiceImpl<VisaDocument, Integer, VisaDocumentDao> implements VisaDocumentService {

	@Override
	protected VisaDocumentDao getDao() {
		return new VisaDocumentDaoImpl(em, se);
	}

}
