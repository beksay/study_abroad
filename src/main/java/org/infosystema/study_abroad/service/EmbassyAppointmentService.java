package org.infosystema.study_abroad.service;

import javax.ejb.Local;

import org.infosystema.study_abroad.model.docs.EmbassyAppointment;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Local
public interface EmbassyAppointmentService extends GenericService<EmbassyAppointment, Integer> {

}
