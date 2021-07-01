package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.VisaPreparationDao;
import org.infosystema.study_abroad.model.docs.VisaPreparation;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class VisaPreparationDaoImpl extends GenericDaoImpl<VisaPreparation, Integer> implements VisaPreparationDao {

	public VisaPreparationDaoImpl(EntityManager entityManager,Event<VisaPreparation> eventSource) {
		super(entityManager,eventSource);
	}
}
