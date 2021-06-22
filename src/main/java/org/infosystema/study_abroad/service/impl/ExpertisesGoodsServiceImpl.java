package org.infosystema.study_abroad.service.impl;

import org.infosystema.study_abroad.dao.ExpertisesGoodsDao;
import org.infosystema.study_abroad.dao.impl.ExpertisesGoodsDaoImpl;
import org.infosystema.study_abroad.model.app.ExpertisesGoods;
import org.infosystema.study_abroad.service.ExpertisesGoodsService;

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
