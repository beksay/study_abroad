package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.docs.Questions;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface QuestionsService extends GenericService<Questions, Integer> {

}
