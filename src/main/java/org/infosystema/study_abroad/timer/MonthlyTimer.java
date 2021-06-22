package org.infosystema.study_abroad.timer;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;



/**
 * 
 * @author MR
 *
 */

@Startup
@Singleton
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
public class MonthlyTimer {
	
	@Inject protected EntityManager em;
	
	@Schedule(dayOfMonth = "1", hour="1")
	private void daily() {
		em.createNamedStoredProcedureQuery("fill_of_work_days").execute();
	}
	
}

