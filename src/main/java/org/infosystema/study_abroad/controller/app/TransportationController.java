package org.infosystema.study_abroad.controller.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.app.Transportations;
import org.infosystema.study_abroad.model.nomenclature.EntryPoint;
import org.infosystema.study_abroad.model.nomenclature.TransportType;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.EntryPointService;
import org.infosystema.study_abroad.service.TransportTypeService;
import org.infosystema.study_abroad.service.TransportationService;

@Named
@ConversationScoped
public class TransportationController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private DictionaryService dictService;
	@EJB
	private EntryPointService entryPointService;
	@EJB
	private TransportTypeService transportTypeService;
	@EJB
	private TransportationService moduleService;

	private Transportations module;
	private Set<EntryPoint> entryPoints;

	@PostConstruct
	public void init() {
		// Empty
	}

	public String edit(Transportations module) {
		this.setModule(module);
		return "transportation_form.xhtml";
	}

	public String save() {
		module.setEntryPoints(entryPoints);
		if (module.getId() == null) {
			module.setStatus(ModuleStatus.NEW);
		} else {
			module.setStatus(ModuleStatus.FILLED);
		}

		module = moduleService.merge(module);

		return "main_app.xhtml";
	}

	public List<Dictionary> getTransportationMethodList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 15, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		return dictService.findByExample(0, 100, examples);
	}

	public List<Dictionary> getCountryList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 2, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<Dictionary> getAddressList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 4, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<TransportType> getTransportTypeList() {
		List<FilterExample> examples = new ArrayList<>();
		return transportTypeService.findByExample(0, 100, examples);
	}

	public List<EntryPoint> getEntryPointList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("id", null, InequalityConstants.IS_NOT_NULL_SINGLE));
		return entryPointService.findByExample(0, 1000, examples);
	}

	public String cancel() {
		return "main_app.xhtml";
	}

	public Transportations getModule() {
		return module;
	}

	public void setModule(Transportations module) {
		this.module = module;
	}

	public Set<EntryPoint> getEntryPoints() {
		return entryPoints;
	}

	public void setEntryPoints(Set<EntryPoint> entryPoints) {
		this.entryPoints = entryPoints;
	}

}
