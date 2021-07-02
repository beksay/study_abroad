package org.infosystema.study_abroad.controller.docs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.Countries;
import org.infosystema.study_abroad.model.Universities;
import org.infosystema.study_abroad.model.UniversityType;
import org.infosystema.study_abroad.model.docs.ApplicationSubmission;
import org.infosystema.study_abroad.model.docs.Applications;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.service.ApplicationSubmissionService;
import org.infosystema.study_abroad.service.ApplicationsService;
import org.infosystema.study_abroad.service.CountriesService;
import org.infosystema.study_abroad.service.UniversitiesService;
import org.infosystema.study_abroad.service.UniversityTypeService;
import org.infosystema.study_abroad.util.web.LoginUtil;


@Named
@ConversationScoped
public class ApplicationSubmissionController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private ApplicationsService service;
	@EJB
	private ApplicationSubmissionService moduleService;
	@EJB
	private CountriesService countryService;
	@EJB
	private UniversityTypeService universityTypeService;
	@EJB
	private UniversitiesService universityService;
	@Inject
	private LoginUtil loginUtil;
	private ApplicationSubmission module;
	private List<Applications> list;
	private Applications applications;
	
	@PostConstruct
	public void init() {
		if (applications==null) applications= new Applications();
	}
	
	public String edit(ApplicationSubmission module) {
		this.module = module;		
		list = service.findByProperty("module", module);
		return "application_submission.xhtml";
	}
	
	public List<Applications> getList() {
		return list;
	}
	
	public List<Applications> getApplicationsList(Person person) {
		return service.findByProperty("module.person", person);
	}
	
	public String save() {
		if(applications.getId()==null) {
			applications.setDateCreated(new Date());
			applications.setUser(loginUtil.getCurrentUser());
			applications.setModule(module);
			applications = service.persist(applications);
			
			list.add(applications);
			
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}
			
			module = moduleService.merge(module);
		}else {
			applications.setDateModify(new Date());
			applications = service.merge(applications);
			list = service.findByProperty("module", applications.getModule());
		}

		applications = new Applications();
		
		return "application_submission.xhtml";
	}
	
	public String editData(Applications applications) {
		this.applications = service.findById(applications.getId(), false);
		return "application_submission.xhtml";
	}
	
	public String delete(Applications applications) {
		
		service.remove(applications);
		
		list = service.findByProperty("module", applications.getModule());
		return "application_submission.xhtml";
	}
	
	public List<Countries> getCountriesList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		Long count = countryService.countByExample(examples);
		return countryService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}
	
	public List<UniversityType> getUniversityTypeList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		Long count = universityTypeService.countByExample(examples);
		return universityTypeService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}
	
	public List<Universities> getUniversitiesList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		if(applications.getCountries()!=null && applications.getUniversityType()!=null) {
			examples.add(new FilterExample("countries", applications.getCountries(), InequalityConstants.EQUAL));
			examples.add(new FilterExample("universityType", applications.getUniversityType(), InequalityConstants.EQUAL));
		}else {
			examples.add(new FilterExample("id", InequalityConstants.IS_NULL_SINGLE));
		}
		Long count = universityService.countByExample(examples);
		return universityService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}
	
	public String cancel() {
		return "main.xhtml";
	}
	
	public ApplicationSubmission getModule() {
		return module;
	}
	
	public void setModule(ApplicationSubmission module) {
		this.module = module;
	}

	public Applications getApplications() {
		return applications;
	}

	public void setApplications(Applications applications) {
		this.applications = applications;
	}
}
