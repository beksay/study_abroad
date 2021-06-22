package org.infosystema.iselect.service;

import javax.ejb.Local;

import org.infosystema.iselect.model.User;
import org.infosystema.iselect.model.app.Applications;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface ApplicationsService extends GenericService<Applications, Integer> {
	
	Applications initialize(User user, Applications applications);

}
