package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ApplicationSubmissionDao;
import org.infosystema.study_abroad.dao.impl.ApplicationSubmissionDaoImpl;
import org.infosystema.study_abroad.model.docs.ApplicationSubmission;
import org.infosystema.study_abroad.service.ApplicationSubmissionService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ApplicationSubmissionService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ApplicationSubmissionServiceImpl extends GenericServiceImpl<ApplicationSubmission, Integer, ApplicationSubmissionDao> implements ApplicationSubmissionService {

	@Override
	protected ApplicationSubmissionDao getDao() {
		return new ApplicationSubmissionDaoImpl(em, se);
	}

}
