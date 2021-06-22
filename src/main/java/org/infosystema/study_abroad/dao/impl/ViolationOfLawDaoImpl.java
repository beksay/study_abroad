package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ViolationOfLawDao;
import org.infosystema.study_abroad.model.nomenclature.ViolationOfLaw;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ViolationOfLawDaoImpl extends GenericDaoImpl<ViolationOfLaw, Integer> implements ViolationOfLawDao {

	public ViolationOfLawDaoImpl(EntityManager entityManager,Event<ViolationOfLaw> eventSource) {
		super(entityManager,eventSource);
	}
}
