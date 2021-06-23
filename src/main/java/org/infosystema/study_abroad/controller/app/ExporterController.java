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
import javax.xml.registry.infomodel.Organization;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.app.Exporters;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.ExporterService;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ConversationScoped
public class ExporterController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private DictionaryService dictService;
	@EJB
	private ExporterService moduleService;

	private Exporters module;

	@PostConstruct
	public void init() {
		// Empty
	}

	public String edit(Exporters module) {
		this.setModule(module);

		return "exporter_form.xhtml";
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

	public List<Dictionary> getLegalFormList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 5, InequalityConstants.EQUAL));

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

	public Exporters getModule() {
		return module;
	}

	public void setModule(Exporters module) {
		this.module = module;
	}

}