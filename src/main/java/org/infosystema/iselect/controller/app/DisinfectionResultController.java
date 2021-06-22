package org.infosystema.iselect.controller.app;

import java.util.ArrayList;
import java.util.List;

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
import org.infosystema.iselect.model.app.DisinfectionResult;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.DisinfectionResultService;
import org.infosystema.iselect.service.DisinfectionService;
import org.infosystema.iselect.util.web.Messages;

@Named
@ConversationScoped
public class DisinfectionResultController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private DisinfectionResultService service;
	@EJB
	private DisinfectionService disinfectionService;
	@EJB
	private DictionaryService dictService;
	private DisinfectionResult result;

	public DisinfectionResultController() {
	}

	@PostConstruct
	public void init() {
		if (result==null) result= new DisinfectionResult();
	}

	public String cancel() {
		result = new DisinfectionResult();
		return list();
	}
	
	public String acceptResult(Disinfection disinfection) { 
		result.setDisinfection(disinfection);
	    return form();
	}
	
	public String view(Disinfection disinfection) { 
		List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("disinfection", disinfection , InequalityConstants.EQUAL));
        List<DisinfectionResult> disinfectionResults = service.findByExample(0, 20,examples);
        result  = disinfectionResults.get(0);
        result.setDisinfection(disinfection);
		return "/view/applications/disinfection/disinfection_preview.xhtml";
	}
	
	public String save() {
		if (result == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}
		if(result.getId()==null) {
			service.persist(result);
		}else {
			service.merge(result);
		}
		result.getDisinfection().setStatus(DisinfectionStatus.ACCEPTED);
		disinfectionService.merge(result.getDisinfection());
		return cancel();
	}
	
	public List<Dictionary> getChemicalList() {
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("type.id",14 , InequalityConstants.EQUAL));
        examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
        return dictService.findByExample(0, 100, examples);
    }
	
	private String list() {
		return "/view/applications/disinfection/disinfection_journal.xhtml";
	}
	
	private String form() {
		return "/view/applications/disinfection/disinfection_result_form.xhtml";
	}

	public DisinfectionResult getResult() {
		return result;
	}
	
	public void setResult(DisinfectionResult result) {
		this.result = result;
	}


}