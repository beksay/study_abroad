package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.OrganizationDao;
import org.infosystema.study_abroad.model.nomenclature.Organization;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class OrganizationDaoImpl extends GenericDaoImpl<Organization, Integer> implements OrganizationDao {

	public OrganizationDaoImpl(EntityManager entityManager,Event<Organization> eventSource) {
		super(entityManager,eventSource);
	}
}
