package org.infosystema.iselect.controller.nomenclature;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.conversiation.ConversationViolationOfLaw;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.nomenclature.ViolationOfLaw;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.ViolationOfLawService;
import org.infosystema.iselect.util.web.Messages;


@ManagedBean
@ViewScoped
public class ViolationOfLawController {

	@EJB
	private ViolationOfLawService service;
	@EJB
	private DictionaryService dictionaryService;
	@Inject
	private ConversationViolationOfLaw conversation;	
	
	private ViolationOfLaw violationOfLaw;
    
	@PostConstruct
	public void init() {
		violationOfLaw=conversation.getViolationOfLaw();
		if (violationOfLaw==null) violationOfLaw= new ViolationOfLaw();
	}
	
	public String add() {
		violationOfLaw = new ViolationOfLaw();
		conversation.setViolationOfLaw(violationOfLaw);
		return form();
	}
	
	public String edit(ViolationOfLaw violationOfLaw) {
		this.violationOfLaw = violationOfLaw;
		conversation.setViolationOfLaw(violationOfLaw);
		return form();
	}
	
	public String save() {
		if(violationOfLaw == null){
			FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("invalidData"), null) );
			return null;
		}
	
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		try {
			if(violationOfLaw.getId() == null) {
				service.persist(violationOfLaw); 
			}else {
				service.merge(violationOfLaw);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setViolationOfLaw(null);
		
	    return cancel();  
		
	}
	
	public List<Dictionary> getControlMeasuresList() {
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("type.id", 9, InequalityConstants.EQUAL));
        examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
        return dictionaryService.findByExample(0, 100, examples);
    }
	
	public String delete(ViolationOfLaw c) {
		service.remove(c);
		return cancel();
	}
	
	public String cancel() {
		violationOfLaw = null;
		return list();
	}
	
	private String list() {
		return "/view/nomenclature/violation_of_law_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/nomenclature/violation_of_law_form.xhtml";
	}

	public ViolationOfLaw getViolationOfLaw() {
		return violationOfLaw;
	}

	public void setViolationOfLaw(ViolationOfLaw violationOfLaw) {
		this.violationOfLaw = violationOfLaw;
	}




}
