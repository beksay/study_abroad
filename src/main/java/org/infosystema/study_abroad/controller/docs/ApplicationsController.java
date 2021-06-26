package org.infosystema.study_abroad.controller.docs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.conversiation.ConversationApp;
import org.infosystema.study_abroad.enums.DocStatus;
import org.infosystema.study_abroad.model.Countries;
import org.infosystema.study_abroad.model.Universities;
import org.infosystema.study_abroad.model.UniversityType;
import org.infosystema.study_abroad.model.docs.Applications;
import org.infosystema.study_abroad.service.ApplicationsService;
import org.infosystema.study_abroad.service.CountriesService;
import org.infosystema.study_abroad.service.UniversitiesService;
import org.infosystema.study_abroad.service.UniversityTypeService;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ViewScoped
public class ApplicationsController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ApplicationsService appService;
	@EJB
	private CountriesService countryService;
	@EJB
	private UniversityTypeService universityTypeService;
	@EJB
	private UniversitiesService universityService;
	@Inject
	private ConversationApp conversation;
	@Inject
	private LoginUtil loginUtil;

	private Applications applications;

	@PostConstruct
	public void init() {
		applications = conversation.getApplications();
		if (applications == null)
			applications = new Applications();
	}

	public String add() {
		applications = new Applications();
		conversation.setApplications(applications);
		return form();
	}

	public String edit(Applications applications) {
		this.applications = applications;
		conversation.setApplications(applications);
		return form();
	}

	public String save() {
		if (applications == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}
		try {
			if (applications.getId() == null) {
				applications.setStatus(DocStatus.NEW);
				applications.setUser(loginUtil.getCurrentUser());
				applications.setDateCreated(new Date());
				appService.persist(applications);
			} else {
				applications.setDateModify(new Date());
				appService.merge(applications);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}

		return cancel();

	}

	public String delete(Applications c) {
		appService.remove(c);
		return cancel();
	}

	public String cancel() { 
		applications = null;
		return list();
	}

	private String list() {
		return "/view/documents/applications_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/documents/applications_form.xhtml";
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

	public Applications getApplications() {
		return applications;
	}

	public void setApplications(Applications applications) {
		this.applications = applications;
	}

}
