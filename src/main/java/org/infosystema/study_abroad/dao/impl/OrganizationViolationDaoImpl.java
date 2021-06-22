package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.OrganizationViolationDao;
import org.infosystema.study_abroad.model.nomenclature.OrganizationViolation;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class OrganizationViolationDaoImpl extends GenericDaoImpl<OrganizationViolation, Integer> implements OrganizationViolationDao {

	public OrganizationViolationDaoImpl(EntityManager entityManager,Event<OrganizationViolation> eventSource) {
		super(entityManager,eventSource);
	}
}
