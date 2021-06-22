package org.infosystema.study_abroad.controller.app;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.app.Importers;
import org.infosystema.study_abroad.model.nomenclature.Organization;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.ImporterService;
import org.infosystema.study_abroad.service.OrganizationService;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ConversationScoped
public class ImporterController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private DictionaryService dictService;
	@EJB
	private ImporterService moduleService;
	@EJB
	private OrganizationService organizationService;

	private Importers module;

	@PostConstruct
	public void init() {
		// Empty
	}

	public String edit(Importers module) {
		this.setModule(module);

		return "importer_form.xhtml";
	}

	public String save() {

		if (module.getId() == null) {
			module.setStatus(ModuleStatus.NEW);
		} else {
			module.setStatus(ModuleStatus.FILLED);
		}

		module = moduleService.merge(module);

		return "main_app.xhtml";
	}

	public void getOrganizationInn() {
		if (module.getInn() != null && module.getInn().length() > 0) {
			List<Organization> organizations = organizationService.findByProperty("inn", module.getInn());
			if (!organizations.isEmpty()) {
				module.setCompanyName(organizations.get(0).getName());
				module.setLegalForm(organizations.get(0).getLegalForm());
				module.setDirectorName(organizations.get(0).getFullname());
				module.setCountry((organizations.get(0).getWorldClassifier()));
				module.setCurrentAddress((organizations.get(0).getAddress()));
				module.setContact((organizations.get(0).getContact()));
				module.setEmail((organizations.get(0).getEmail()));
			} else {
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("ИНН not found !!! ", "ИНН not found !!! "));
			}
		} else {
			FacesContext.getCurrentInstance().addMessage("form", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getMessage("ИНН Это обязательное поле"), null));
		}

	}

	public List<Dictionary> getLegalFormList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 5, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<Dictionary> getCountryList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 2, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}
	
	public List<Dictionary> getTaxpayerList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 16, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);

		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public String cancel() {
		return "main_app.xhtml";
	}

	public Importers getModule() {
		return module;
	}

	public void setModule(Importers module) {
		this.module = module;
	}

}
