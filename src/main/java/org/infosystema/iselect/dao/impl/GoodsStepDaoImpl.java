package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.GoodsStepDao;
import org.infosystema.iselect.model.app.GoodStep;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class GoodsStepDaoImpl extends GenericDaoImpl<GoodStep, Integer> implements GoodsStepDao {

	public GoodsStepDaoImpl(EntityManager entityManager,Event<GoodStep> eventSource) {
		super(entityManager,eventSource);
	}
}
