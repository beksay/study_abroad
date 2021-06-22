package org.infosystema.iselect.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.GoodsDao;
import org.infosystema.iselect.dao.impl.GoodsDaoImpl;
import org.infosystema.iselect.model.app.Goods;
import org.infosystema.iselect.service.GoodsService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(GoodsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class GoodsServiceImpl extends GenericServiceImpl<Goods, Integer, GoodsDao> implements GoodsService {

	@Override
	protected GoodsDao getDao() {
		return new GoodsDaoImpl(em, se);
	}

}
