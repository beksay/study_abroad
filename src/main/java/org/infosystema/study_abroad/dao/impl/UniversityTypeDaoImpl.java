package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.UniversityTypeDao;
import org.infosystema.study_abroad.model.UniversityType;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class UniversityTypeDaoImpl extends GenericDaoImpl<UniversityType, Integer> implements UniversityTypeDao {

	public UniversityTypeDaoImpl(EntityManager entityManager,Event<UniversityType> eventSource) {
		super(entityManager,eventSource);
	}
}
