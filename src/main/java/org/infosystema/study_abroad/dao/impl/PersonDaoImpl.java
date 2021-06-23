package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.PersonDao;
import org.infosystema.study_abroad.model.Person;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class PersonDaoImpl extends GenericDaoImpl<Person, Integer> implements PersonDao {

	public PersonDaoImpl(EntityManager entityManager,Event<Person> eventSource) {
		super(entityManager,eventSource);
	}
}
