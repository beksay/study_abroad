package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.docs.Visa;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface VisaService extends GenericService<Visa, Integer> {

}
