package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.app.Storage;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface StorageService extends GenericService<Storage, Integer> {

}
