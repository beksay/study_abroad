package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.nomenclature.Products;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface ProductsService extends GenericService<Products, Integer> {

}
