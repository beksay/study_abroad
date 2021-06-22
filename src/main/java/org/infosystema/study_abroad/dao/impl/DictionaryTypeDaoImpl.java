package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.DictionaryTypeDao;
import org.infosystema.study_abroad.model.DictionaryType;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class DictionaryTypeDaoImpl extends GenericDaoImpl<DictionaryType, Integer> implements DictionaryTypeDao {

	public DictionaryTypeDaoImpl(EntityManager entityManager,Event<DictionaryType> eventSource) {
		super(entityManager,eventSource);
	}
}
