package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.docs.VisaQuestion;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface VisaQuestionService extends GenericService<VisaQuestion, Integer> {

}
