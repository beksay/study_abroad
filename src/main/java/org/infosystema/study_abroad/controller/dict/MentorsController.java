package org.infosystema.study_abroad.controller.dict;

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
import org.infosystema.study_abroad.conversiation.ConversationMentors;
import org.infosystema.study_abroad.model.Countries;
import org.infosystema.study_abroad.model.Mentors;
import org.infosystema.study_abroad.service.CountriesService;
import org.infosystema.study_abroad.service.MentorsService;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ViewScoped
public class MentorsController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private MentorsService mentorsService;
	@EJB
	private CountriesService countriesService;
	@Inject
	private ConversationMentors conversation;
	@Inject
	private LoginUtil loginUtil;

	private Mentors mentors;

	@PostConstruct
	public void init() {
		mentors = conversation.getMentors();
		if (mentors == null)
			mentors = new Mentors();
	}

	public String add() {
		mentors = new Mentors();
		conversation.setMentors(mentors);
		return form();
	}

	public String back() {
		mentors = new Mentors();
		conversation.setMentors(null);
		return "/view/mentors/mentors_list.xhtml?faces-redirect=true";
	}

	public String edit(Mentors mentors) {
		this.mentors = mentors;
		conversation.setMentors(mentors);
		return form();
	}

	public String save() {
		if (mentors == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}

		if (!FacesContext.getCurrentInstance().getMessageList().isEmpty())
			return null;
		try {
			if (mentors.getId() == null) {
				mentors.setCompany(loginUtil.getCurrentUser());
				mentors.setDateCreated(new Date());
				mentorsService.persist(mentors);
			} else {
				mentorsService.merge(mentors);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setMentors(null);

		return cancel();

	}

	public String delete(Mentors c) {
		mentorsService.remove(c);
		return cancel();
	}

	public String cancel() {
		mentors = null;
		return list();
	}

	private String list() {
		return "/view/mentors/mentors_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/mentors/mentors_form.xhtml";
	}
	
	public List<Countries> getCountryList(String query) {

		List<FilterExample> examples = new ArrayList<>();
		Long count = countriesService.countByExample(examples);

		return countriesService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public Mentors getMentors() {
		return mentors;
	}
	
	public void setMentors(Mentors mentors) {
		this.mentors = mentors;
	}

}
