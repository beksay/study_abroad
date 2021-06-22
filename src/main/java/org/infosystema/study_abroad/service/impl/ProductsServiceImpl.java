package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.ProductsDao;
import org.infosystema.study_abroad.dao.impl.ProductsDaoImpl;
import org.infosystema.study_abroad.model.nomenclature.Products;
import org.infosystema.study_abroad.service.ProductsService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ProductsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ProductsServiceImpl extends GenericServiceImpl<Products, Integer, ProductsDao> implements ProductsService {

	@Override
	protected ProductsDao getDao() {
		return new ProductsDaoImpl(em, se);
	}

}
