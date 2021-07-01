package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.docs.Person;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface PersonService extends GenericService<Person, Integer> {

}
