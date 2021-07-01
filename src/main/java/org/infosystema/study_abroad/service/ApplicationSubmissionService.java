package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.docs.ApplicationSubmission;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface ApplicationSubmissionService extends GenericService<ApplicationSubmission, Integer> {

}
