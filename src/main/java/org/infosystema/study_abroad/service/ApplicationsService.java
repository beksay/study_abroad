package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.docs.Applications;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface ApplicationsService extends GenericService<Applications, Integer> {

}
