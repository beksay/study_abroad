package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.QuestionsDao;
import org.infosystema.study_abroad.dao.impl.QuestionsDaoImpl;
import org.infosystema.study_abroad.model.docs.Questions;
import org.infosystema.study_abroad.service.QuestionsService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(QuestionsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class QuestionsServiceImpl extends GenericServiceImpl<Questions, Integer, QuestionsDao> implements QuestionsService {

	@Override
	protected QuestionsDao getDao() {
		return new QuestionsDaoImpl(em, se);
	}

}
