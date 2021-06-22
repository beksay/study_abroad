package org.infosystema.iselect.controller.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.enums.DisinfectionStatus;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.app.Disinfection;
import org.infosystema.iselect.model.app.Inspection;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.DisinfectionService;
import org.infosystema.iselect.service.InspectionService;
import org.infosystema.iselect.util.web.Messages;

@Named
@ConversationScoped
public class DisinfectionController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private DisinfectionService service;
	@EJB
	private InspectionService inspectionService;
	@EJB
	private DictionaryService dictService;
	private Disinfection disinfection;

	public DisinfectionController() {
	}

	@PostConstruct
	public void init() {
		if (disinfection==null) disinfection= new Disinfection();
	}

	public String save() {
		if (disinfection == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}
		disinfection.setStatus(DisinfectionStatus.SENDED_DISINFECTION);
		disinfection.setDate(new Date());
		if(disinfection.getId()==null) {
			service.persist(disinfection);
		}else {
			service.merge(disinfection);
		}
		disinfection.getInspection().setDisinfectionStatus(DisinfectionStatus.SENDED_DISINFECTION);
		inspectionService.merge(disinfection.getInspection());
		return cancel();
	}


	public String cancel() {
		System.out.println("canceling.....");
		setDisinfection(null);
		return list();
	}

	public String acceptDisinfection(Inspection inspection) { 
		disinfection = new Disinfection();
		disinfection.setInspection(inspection);
	    return form();
	}

	private String list() {
		return "/view/applications/inspection/inspection_journal.xhtml";
	}

	private String form() {
		return "/view/applications/disinfection/disinfection_form.xhtml";
	}
	
	public List<Dictionary> getMeasurementUnitList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 1, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);

		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public Disinfection getDisinfection() {
		return disinfection;
	}
	
	public void setDisinfection(Disinfection disinfection) {
		this.disinfection = disinfection;
	}
	
}