package org.infosystema.study_abroad.service.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.EmbassyAppointmentDao;
import org.infosystema.study_abroad.dao.impl.EmbassyAppointmentDaoImpl;
import org.infosystema.study_abroad.model.docs.EmbassyAppointment;
import org.infosystema.study_abroad.service.EmbassyAppointmentService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(EmbassyAppointmentService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class EmbassyAppointmentServiceImpl extends GenericServiceImpl<EmbassyAppointment, Integer, EmbassyAppointmentDao> implements EmbassyAppointmentService {

	@Override
	protected EmbassyAppointmentDao getDao() {
		return new EmbassyAppointmentDaoImpl(em, se);
	}

}
