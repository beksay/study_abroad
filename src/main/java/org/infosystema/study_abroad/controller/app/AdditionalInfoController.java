package org.infosystema.study_abroad.controller.app;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.app.AdditionalInfo;
import org.infosystema.study_abroad.service.AdditionalInfoService;

@Named
@ConversationScoped
public class AdditionalInfoController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private AdditionalInfoService moduleService;
	
	private AdditionalInfo module;
	
	@PostConstruct
	public void init() {
		
	}
	
	public String edit(AdditionalInfo module) {
		this.setModule(module);

		return "additional_info_form.xhtml";
	}
	
	public String save() {

		if(module.getId()==null) {
			module.setStatus(ModuleStatus.NEW);
		} else {
			module.setStatus(ModuleStatus.FILLED);
		}
		
		module = moduleService.merge(module);

		return "main_app.xhtml";
	}
	
	public String cancel() {
		return "main_app.xhtml";
	}

    public AdditionalInfo getModule() {
		return module;
	}
    
    public void setModule(AdditionalInfo module) {
		this.module = module;
	}
	
}
