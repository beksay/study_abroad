package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.MentorsDao;
import org.infosystema.study_abroad.dao.impl.MentorsDaoImpl;
import org.infosystema.study_abroad.model.Mentors;
import org.infosystema.study_abroad.service.MentorsService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(MentorsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class MentorsServiceImpl extends GenericServiceImpl<Mentors, Integer, MentorsDao> implements MentorsService {

	@Override
	protected MentorsDao getDao() {
		return new MentorsDaoImpl(em, se);
	}

}
