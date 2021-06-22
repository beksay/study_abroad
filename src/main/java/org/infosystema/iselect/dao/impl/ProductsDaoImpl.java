package org.infosystema.iselect.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.iselect.dao.ProductsDao;
import org.infosystema.iselect.model.nomenclature.Products;

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
