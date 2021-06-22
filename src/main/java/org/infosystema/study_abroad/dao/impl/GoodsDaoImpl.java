package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.GoodsDao;
import org.infosystema.study_abroad.model.app.Goods;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class GoodsDaoImpl extends GenericDaoImpl<Goods, Integer> implements GoodsDao {

	public GoodsDaoImpl(EntityManager entityManager,Event<Goods> eventSource) {
		super(entityManager,eventSource);
	}
}
