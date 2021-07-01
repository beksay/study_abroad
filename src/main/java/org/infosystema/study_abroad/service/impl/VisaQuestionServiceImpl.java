package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.VisaQuestionDao;
import org.infosystema.study_abroad.dao.impl.VisaQuestionDaoImpl;
import org.infosystema.study_abroad.model.docs.VisaQuestion;
import org.infosystema.study_abroad.service.VisaQuestionService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(VisaQuestionService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class VisaQuestionServiceImpl extends GenericServiceImpl<VisaQuestion, Integer, VisaQuestionDao> implements VisaQuestionService {

	@Override
	protected VisaQuestionDao getDao() {
		return new VisaQuestionDaoImpl(em, se);
	}

}
