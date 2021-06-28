package org.infosystema.study_abroad.controller.dict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.controller.FileUploadController;
import org.infosystema.study_abroad.conversiation.ConversationUniversities;
import org.infosystema.study_abroad.dto.AttachmentBinaryDTO;
import org.infosystema.study_abroad.model.Attachment;
import org.infosystema.study_abroad.model.Universities;
import org.infosystema.study_abroad.model.UniversityType;
import org.infosystema.study_abroad.service.UniversitiesService;
import org.infosystema.study_abroad.service.UniversityTypeService;
import org.infosystema.study_abroad.util.Util;
import org.infosystema.study_abroad.util.web.LoginUtil;

@Named
@ViewScoped
public class UniversitiesController implements Serializable {

	private static final long serialVersionUID = 1L;
	@EJB
	private UniversitiesService universitiesService;
	@EJB
	private UniversityTypeService universityTypeService;
	@Inject
	private ConversationUniversities conversation;
	@Inject
	private CountriesController controller;
	@Inject
	private FileUploadController uploadController;
	@Inject
	private LoginUtil loginUtil;

	private Universities universities;

	@PostConstruct
	public void init() {
		if (universities == null)
			universities = new Universities();
		universities = conversation.getUniversities();
	}

	public String add() {
		universities = new Universities();
		conversation.setUniversities(universities);
		uploadController.setFiles(new ArrayList<AttachmentBinaryDTO>());
		return form();
	}

	public String back() {
		universities = new Universities();
		conversation.setUniversities(null);
		return "/view/dictionaries/countries_list.xhtml?faces-redirect=true";
	}

	public String edit(Universities universities) {
		universities = universitiesService.getByIdWithFields(universities.getId(), new String[]{"attachments"});
		this.universities = universities;
		conversation.setUniversities(universities);
		uploadController.setFiles(Util.getFiles(universities.getAttachments()));
		return form();
	}

	public String save() {

		Set<Attachment> attachments = new HashSet<>();
		try {
			attachments.addAll(uploadController.convert());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		
		universities.setAttachments(attachments);
		universities.setCompany(loginUtil.getCurrentUser());
		if (universities.getId() == null) {
			universities.setCountries(controller.getCountries());
			universitiesService.persist(universities);
		} else {
			universitiesService.merge(universities);
		}

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
		System.out.println("=======BACK=======");
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
