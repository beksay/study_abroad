package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.DictionaryTypeDao;
import org.infosystema.iselect.model.DictionaryType;

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
