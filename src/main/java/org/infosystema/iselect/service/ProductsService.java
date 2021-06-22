package org.infosystema.iselect.service;

import javax.ejb.Local;

import org.infosystema.iselect.model.nomenclature.Products;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface ProductsService extends GenericService<Products, Integer> {

}
