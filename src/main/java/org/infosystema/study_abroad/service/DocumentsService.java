package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.docs.Documents;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface DocumentsService extends GenericService<Documents, Integer> {

}
