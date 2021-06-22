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
import org.infosystema.iselect.conversiation.ConversationTransportType;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.nomenclature.TransportType;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.TransportTypeService;
import org.infosystema.iselect.util.web.Messages;


@ManagedBean
@ViewScoped
public class TransportTypeController {

	@EJB
	private TransportTypeService service;
	@EJB
	private DictionaryService dictionaryService;
	@Inject
	private ConversationTransportType conversation;	
	
	private TransportType transportType;
    
	@PostConstruct
	public void init() {
		transportType=conversation.getTransportType();
		if (transportType==null) transportType= new TransportType();
	}
	
	public String add() {
		transportType = new TransportType();
		conversation.setTransportType(transportType);
		return form();
	}
	
	public String edit(TransportType transportType) {
		this.transportType = transportType;
		conversation.setTransportType(transportType);
		return form();
	}
	
	public String save() {
		if(transportType == null){
			FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("invalidData"), null) );
			return null;
		}
	
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		try {
			if(transportType.getId() == null) {
				service.persist(transportType); 
			}else {
				service.merge(transportType);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setTransportType(null);
		
	    return cancel();  
		
	}

	public List<Dictionary> getTransportationMethodList() {
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("type.id", 15, InequalityConstants.EQUAL));
        examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
        return dictionaryService.findByExample(0, 100, examples);
    }
	
	public String delete(TransportType c) {
		service.remove(c);
		return cancel();
	}
	
	public String cancel() {
		transportType = null;
		return list();
	}
	
	private String list() {
		return "/view/nomenclature/transport_type_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/nomenclature/transport_type_form.xhtml";
	}

	public TransportType getTransportType() {
		return transportType;
	}
	
	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}


}
