package org.infosystema.study_abroad.dao.impl;

import org.infosystema.study_abroad.dao.ExpertisesGoodsDao;
import org.infosystema.study_abroad.model.app.ExpertisesGoods;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;


public class ExpertisesGoodsDaoImpl extends GenericDaoImpl<ExpertisesGoods, Integer> implements ExpertisesGoodsDao {

	public ExpertisesGoodsDaoImpl(EntityManager entityManager, Event<ExpertisesGoods> eventSource) {
		super(entityManager,eventSource);
	}
}
