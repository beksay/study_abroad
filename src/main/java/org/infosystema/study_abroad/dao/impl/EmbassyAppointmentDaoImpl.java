package org.infosystema.study_abroad.dao.impl;

import javax.enterprise.event.Event;
import javax.persistence.EntityManager;

import org.infosystema.study_abroad.dao.EmbassyAppointmentDao;
import org.infosystema.study_abroad.model.docs.EmbassyAppointment;

/****
 * 
 * @author Akzholbek Omorov
 *
 */
public class EmbassyAppointmentDaoImpl extends GenericDaoImpl<EmbassyAppointment, Integer> implements EmbassyAppointmentDao {

	public EmbassyAppointmentDaoImpl(EntityManager entityManager,Event<EmbassyAppointment> eventSource) {
		super(entityManager,eventSource);
	}
}
