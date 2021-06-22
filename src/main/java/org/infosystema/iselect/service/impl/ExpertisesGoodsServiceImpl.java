package org.infosystema.iselect.service.impl;

import org.infosystema.iselect.dao.ExpertisesGoodsDao;
import org.infosystema.iselect.dao.impl.ExpertisesGoodsDaoImpl;
import org.infosystema.iselect.model.app.ExpertisesGoods;
import org.infosystema.iselect.service.ExpertisesGoodsService;

import javax.ejb.*;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ExpertisesGoodsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ExpertisesGoodsServiceImpl extends GenericServiceImpl<ExpertisesGoods, Integer, ExpertisesGoodsDao> implements ExpertisesGoodsService {

	@Override
	protected ExpertisesGoodsDao getDao() {
		return new ExpertisesGoodsDaoImpl(em, se);
	}

}
