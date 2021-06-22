package org.infosystema.iselect.dao.impl;

import org.infosystema.iselect.dao.ExpertisesGoodsDao;
import org.infosystema.iselect.model.app.ExpertisesGoods;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;


public class ExpertisesGoodsDaoImpl extends GenericDaoImpl<ExpertisesGoods, Integer> implements ExpertisesGoodsDao {

	public ExpertisesGoodsDaoImpl(EntityManager entityManager, Event<ExpertisesGoods> eventSource) {
		super(entityManager,eventSource);
	}
}
