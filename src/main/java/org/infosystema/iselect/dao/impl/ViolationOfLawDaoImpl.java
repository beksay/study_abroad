package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.ViolationOfLawDao;
import org.infosystema.iselect.model.nomenclature.ViolationOfLaw;

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
