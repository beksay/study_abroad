package org.infosystema.iselect.service.impl;

import java.util.Calendar;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.iselect.dao.ApplicationsDao;
import org.infosystema.iselect.dao.impl.ApplicationsDaoImpl;
import org.infosystema.iselect.model.User;
import org.infosystema.iselect.model.app.AdditionalInfo;
import org.infosystema.iselect.model.app.AppModule;
import org.infosystema.iselect.model.app.Applications;
import org.infosystema.iselect.model.app.DocumentsStep;
import org.infosystema.iselect.model.app.Exporters;
import org.infosystema.iselect.model.app.GoodStep;
import org.infosystema.iselect.model.app.Importers;
import org.infosystema.iselect.model.app.Transportations;
import org.infosystema.iselect.service.ApplicationsService;
import org.infosystema.iselect.enums.AppStatus;
import org.infosystema.iselect.enums.ModuleStatus;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(ApplicationsService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ApplicationsServiceImpl extends GenericServiceImpl<Applications, Integer, ApplicationsDao> implements ApplicationsService {

	@Override
	protected ApplicationsDao getDao() {
		return new ApplicationsDaoImpl(em, se);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Applications initialize(User user, Applications applications) {
		applications.setStatus(AppStatus.NEW);
        applications.setYear(Calendar.getInstance().get(Calendar.YEAR));
		applications = getDao().persist(applications);
		
		AppModule module = new Exporters();
		
		createModule(applications, module, 1);
		
		module = new Importers();
		createModule(applications, module, 2);
		
		module = new Transportations();
		createModule(applications, module, 3); 
		
		module = new GoodStep();
		createModule(applications, module, 4);
		
		module = new DocumentsStep();
		createModule(applications, module, 5);
		
		module = new AdditionalInfo();
		createModule(applications, module, 6);
				
		return applications;
	}

	private void createModule(Applications applications, AppModule module, int index) {
		module.setApplications(applications);
		module.setIndex(index);
		module.setStatus(ModuleStatus.NEW);
		em.persist(module);
	}

}
