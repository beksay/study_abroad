package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ApplicationFeeDao;
import org.infosystema.study_abroad.model.docs.ApplicationFee;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ApplicationFeeDaoImpl extends GenericDaoImpl<ApplicationFee, Integer> implements ApplicationFeeDao {

	public ApplicationFeeDaoImpl(EntityManager entityManager,Event<ApplicationFee> eventSource) {
		super(entityManager,eventSource);
	}
}
