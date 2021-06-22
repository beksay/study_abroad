package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.model.app.Applications;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface ApplicationsService extends GenericService<Applications, Integer> {
	
	Applications initialize(User user, Applications applications);

}
