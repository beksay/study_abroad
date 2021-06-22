package org.infosystema.iselect.controller.nomenclature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.SystemException;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.controller.FileUploadController;
import org.infosystema.iselect.conversiation.ConversationQuarantineObject;
import org.infosystema.iselect.model.Attachment;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.nomenclature.QuarantineObject;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.QuarantineObjectService;
import org.infosystema.iselect.util.web.Messages;


@ManagedBean
@ViewScoped
public class QuarantineObjectController {

	@EJB
	private QuarantineObjectService service;
	@EJB
	private DictionaryService dictionaryService;
	@Inject
	private ConversationQuarantineObject conversation;	
	
	private QuarantineObject quarantineObject;
    
	@PostConstruct
	public void init() {
		quarantineObject=conversation.getQuarantineObject();
		if (quarantineObject==null)	quarantineObject= new QuarantineObject();
	}
	
	public String add() {
		quarantineObject = new QuarantineObject();
		conversation.setQuarantineObject(quarantineObject);
		return form();
	}
	
	public String edit(QuarantineObject quarantineObject) {
		this.quarantineObject = quarantineObject;
		conversation.setQuarantineObject(quarantineObject);
		return form();
	}
	
	public String save() {
		if(quarantineObject == null){
			FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("invalidData"), null) );
			return null;
		}
	
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		try {
			if(quarantineObject.getId() == null) {
				service.persist(quarantineObject); 
			}else {
				service.merge(quarantineObject);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setQuarantineObject(null);
		
	    return cancel();  
		
	}
	
	public List<Dictionary> getQuarantineTypeList() {
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("type.id", 10, InequalityConstants.EQUAL));
        examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
        return dictionaryService.findByExample(0, 100, examples);
    }

	
	public String delete(QuarantineObject c) {
		service.remove(c);
		return cancel();
	}
	
	public String cancel() {
		quarantineObject = null;
		return list();
	}
	
	private String list() {
		return "/view/nomenclature/quarantine_object_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/nomenclature/quarantine_object_form.xhtml";
	}

	public QuarantineObject getQuarantineObject() {
		return quarantineObject;
	}
	
	public void setQuarantineObject(QuarantineObject quarantineObject) {
		this.quarantineObject = quarantineObject;
	}


}
