package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.CountriesDao;
import org.infosystema.study_abroad.model.Countries;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class CountriesDaoImpl extends GenericDaoImpl<Countries, Integer> implements CountriesDao {

	public CountriesDaoImpl(EntityManager entityManager,Event<Countries> eventSource) {
		super(entityManager,eventSource);
	}
}
