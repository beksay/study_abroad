package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.app.Certificate;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface CertificateService extends GenericService<Certificate, Integer> {

}
