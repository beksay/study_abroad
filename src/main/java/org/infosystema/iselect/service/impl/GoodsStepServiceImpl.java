package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.GoodsStepDao;
import org.infosystema.iselect.dao.impl.GoodsStepDaoImpl;
import org.infosystema.iselect.model.app.GoodStep;
import org.infosystema.iselect.service.GoodsStepService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(GoodsStepService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class GoodsStepServiceImpl extends GenericServiceImpl<GoodStep, Integer, GoodsStepDao> implements GoodsStepService {

	@Override
	protected GoodsStepDao getDao() {
		return new GoodsStepDaoImpl(em, se);
	}

}
