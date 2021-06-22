package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.ProductsDao;
import org.infosystema.study_abroad.model.nomenclature.Products;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class ProductsDaoImpl extends GenericDaoImpl<Products, Integer> implements ProductsDao {

	public ProductsDaoImpl(EntityManager entityManager,Event<Products> eventSource) {
		super(entityManager,eventSource);
	}
}
