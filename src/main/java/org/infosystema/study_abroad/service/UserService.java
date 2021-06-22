package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.User;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface UserService extends GenericService<User, Integer> {

}
