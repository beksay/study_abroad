package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.app.Goods;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface GoodsService extends GenericService<Goods, Integer> {

}
