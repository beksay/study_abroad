package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.DictionaryDao;
import org.infosystema.iselect.model.Dictionary;

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
