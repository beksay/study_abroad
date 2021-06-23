package org.infosystema.study_abroad.controller.dict;

import java.io.Serializable;
import java.util.ArrayList;
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
import org.infosystema.study_abroad.conversiation.ConversationUniversities;
import org.infosystema.study_abroad.model.Universities;
import org.infosystema.study_abroad.model.UniversityType;
import org.infosystema.study_abroad.service.UniversitiesService;
import org.infosystema.study_abroad.service.UniversityTypeService;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ViewScoped
public class UniversitiesController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private UniversitiesService universitiesService;
	@EJB
	private UniversityTypeService universityTypeService;
	@Inject
	private ConversationUniversities conversation;
	@Inject
	private CountriesController controller;

	private Universities universities;

	@PostConstruct
	public void init() {
		universities = conversation.getUniversities();
		if (universities == null)
			universities = new Universities();
	}

	public String add() {
		universities = new Universities();
		conversation.setUniversities(universities);
		return form();
	}

	public String back() {
		universities = new Universities();
		conversation.setUniversities(null);
		return "/view/dictionaries/countries_list.xhtml?faces-redirect=true";
	}

	public String edit(Universities universities) {
		this.universities = universities;
		conversation.setUniversities(universities);
		return form();
	}

	public String save() {
		if (universities == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}

		if (!FacesContext.getCurrentInstance().getMessageList().isEmpty())
			return null;
		try {
			if (universities.getId() == null) {
				universities.setCountries(controller.getCountries());
				universitiesService.persist(universities);
			} else {
				universitiesService.merge(universities);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setUniversities(null);

		return cancel();

	}

	public List<UniversityType> getUniversityTypeList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		Long count = universityTypeService.countByExample(examples);
		return universityTypeService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public String delete(Universities c) {
		universitiesService.remove(c);
		return cancel();
	}

	public String cancel() {
		universities = null;
		return list();
	}

	private String list() {
		return "/view/dictionaries/universities_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/dictionaries/universities_form.xhtml";
	}

	public CountriesController getController() {
		return controller;
	}
	
	public void setController(CountriesController controller) {
		this.controller = controller;
	}

	public Universities getUniversities() {
		return universities;
	}

	public void setUniversities(Universities universities) {
		this.universities = universities;
	}

}
