package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.Countries;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface CountriesService extends GenericService<Countries, Integer> {

}
