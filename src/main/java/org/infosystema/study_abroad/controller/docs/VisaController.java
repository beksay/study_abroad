package org.infosystema.study_abroad.controller.docs;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.model.docs.Visa;
import org.infosystema.study_abroad.model.docs.VisaStep;
import org.infosystema.study_abroad.service.VisaService;
import org.infosystema.study_abroad.service.VisaStepService;
import org.infosystema.study_abroad.util.web.LoginUtil;


@Named
@ConversationScoped
public class VisaController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private VisaService service;
	@EJB
	private VisaStepService moduleService;
	@Inject
	private LoginUtil loginUtil;
	private VisaStep module;
	private List<Visa> list;
	private Visa visa;
	
	@PostConstruct
	public void init() {
		if (visa==null) visa= new Visa();
	}
	
	public String edit(VisaStep module) {
		this.module = module;		
		list = service.findByProperty("module", module);
		return "visa.xhtml";
	}
	
	public List<Visa> getList() {
		return list;
	}
	
	public List<Visa> getVisaList(Person person) {
		return service.findByProperty("module.person", person);
	}
	
	public String save() {
		if(visa.getId()==null) {
			visa.setDate(new Date());
			visa.setUser(loginUtil.getCurrentUser());
			visa.setDateCreated(new Date());
			visa.setModule(module);
			visa = service.persist(visa);
			
			list.add(visa);
			
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}	
			module = moduleService.merge(module);
		}else {
			visa = service.merge(visa);
			list = service.findByProperty("module", visa.getModule());
		}

		visa = new Visa();
		
		return "visa.xhtml";
	}
	
	public String editData(Visa visa) {
		this.visa = service.findById(visa.getId(), false);
		return "visa.xhtml";
	}
	
	public String delete(Visa visa) {
		
		service.remove(visa);
		
		list = service.findByProperty("module", visa.getModule());
		return "visa.xhtml";
	}
	
	public String cancel() {
		return "main.xhtml";
	}
	
	public VisaStep getModule() {
		return module;
	}
	
	public void setModule(VisaStep module) {
		this.module = module;
	}

	public Visa getVisa() {
		return visa;
	}

	public void setVisa(Visa visa) {
		this.visa = visa;
	}
}
