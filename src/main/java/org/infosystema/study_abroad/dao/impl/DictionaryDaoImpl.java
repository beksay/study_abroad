package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.DictionaryDao;
import org.infosystema.study_abroad.model.Dictionary;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class DictionaryDaoImpl extends GenericDaoImpl<Dictionary, Integer> implements DictionaryDao {

	public DictionaryDaoImpl(EntityManager entityManager,Event<Dictionary> eventSource) {
		super(entityManager,eventSource);
	}
}
