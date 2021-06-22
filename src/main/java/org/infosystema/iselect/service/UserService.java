package org.infosystema.iselect.service;

import javax.ejb.Local;

import org.infosystema.iselect.model.User;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface UserService extends GenericService<User, Integer> {

}
