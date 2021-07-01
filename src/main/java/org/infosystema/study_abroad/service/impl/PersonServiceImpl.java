package org.infosystema.study_abroad.service.impl;

import java.util.Calendar;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.infosystema.study_abroad.dao.PersonDao;
import org.infosystema.study_abroad.dao.impl.PersonDaoImpl;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.enums.PersonStatus;
import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.model.docs.ApplicationFee;
import org.infosystema.study_abroad.model.docs.ApplicationSubmission;
import org.infosystema.study_abroad.model.docs.ContractSigned;
import org.infosystema.study_abroad.model.docs.DocumentsStep;
import org.infosystema.study_abroad.model.docs.Ds160;
import org.infosystema.study_abroad.model.docs.EmbassyAppointment;
import org.infosystema.study_abroad.model.docs.ITwenty;
import org.infosystema.study_abroad.model.docs.Module;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.model.docs.Reasons;
import org.infosystema.study_abroad.model.docs.VisaDocument;
import org.infosystema.study_abroad.model.docs.VisaPreparation;
import org.infosystema.study_abroad.model.docs.VisaQuestion;
import org.infosystema.study_abroad.model.docs.VisaStep;
import org.infosystema.study_abroad.service.PersonService;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Stateless
@Local(PersonService.class)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class PersonServiceImpl extends GenericServiceImpl<Person, Integer, PersonDao> implements PersonService {

	@Override
	protected PersonDao getDao() {
		return new PersonDaoImpl(em, se);
	}
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Person initialize(User user,Person person) {
		person.setStatus(PersonStatus.NEW);
        person.setYear(Calendar.getInstance().get(Calendar.YEAR));
		person = getDao().persist(person);
		
		Module module = new ContractSigned();
		
		createModule(person, module, 1);
		
		module = new DocumentsStep();
		createModule(person, module, 2);
		
		module = new ApplicationFee();
		createModule(person, module, 3);
		
		module = new ApplicationSubmission();
		createModule(person, module, 4);
		
		module = new ITwenty();
		createModule(person, module, 5);
		
		module = new Ds160();
		createModule(person, module, 6);
		
		module = new EmbassyAppointment();
		createModule(person, module, 7);
		
		module = new VisaPreparation();
		createModule(person, module, 8);
		
		module = new VisaDocument();
		createModule(person, module, 9);
		
		module = new VisaStep();
		createModule(person, module, 10);
		
		module = new VisaQuestion();
		createModule(person, module, 11);
		
		module = new Reasons();
		createModule(person, module, 12);
		return person;
	}

	private void createModule(Person person, Module module, int index) {
		module.setPerson(person);
		module.setIndex(index);
		module.setStatus(ModuleStatus.NEW);
		em.persist(module);
	}

}
