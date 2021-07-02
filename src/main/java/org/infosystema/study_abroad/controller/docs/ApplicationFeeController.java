package org.infosystema.study_abroad.controller.docs;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.CurrencyType;
import org.infosystema.study_abroad.enums.FeeType;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.docs.ApplicationFee;
import org.infosystema.study_abroad.model.docs.MoneySimulation;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.service.ApplicationFeeService;
import org.infosystema.study_abroad.service.MoneySimulationService;
import org.infosystema.study_abroad.util.web.LoginUtil;


@Named
@ConversationScoped
public class ApplicationFeeController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private MoneySimulationService service;
	@EJB
	private ApplicationFeeService moduleService;
	@Inject
	private LoginUtil loginUtil;
	private ApplicationFee module;
	private List<MoneySimulation> list;
	private MoneySimulation moneySimulation;
	
	@PostConstruct
	public void init() {
		if (moneySimulation==null) moneySimulation= new MoneySimulation();
	}
	
	public String edit(ApplicationFee module) {
		this.module = module;		
		list = service.findByProperty("module", module);
		return "application_fee.xhtml";
	}
	
	public List<MoneySimulation> getList() {
		return list;
	}
	
	public List<MoneySimulation> getMoneySimulationList(Person person) {
		return service.findByProperty("module.person", person);
	}
	
	public String save() {
		if(moneySimulation.getId()==null) {
			moneySimulation.setDateCreated(new Date());
			moneySimulation.setUser(loginUtil.getCurrentUser());
			moneySimulation.setModule(module);
			moneySimulation = service.persist(moneySimulation);
			
			list.add(moneySimulation);
			
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}
			
			module = moduleService.merge(module);
		}else {
			moneySimulation.setDateModify(new Date());
			moneySimulation = service.merge(moneySimulation);
			list = service.findByProperty("module", moneySimulation.getModule());
		}

		moneySimulation = new MoneySimulation();
		
		return "application_fee.xhtml";
	}
	
	public String editData(MoneySimulation moneySimulation) {
		this.moneySimulation = service.findById(moneySimulation.getId(), false);
		return "application_fee.xhtml";
	}
	
	public String delete(MoneySimulation moneySimulation) {
		
		service.remove(moneySimulation);
		
		list = service.findByProperty("module", moneySimulation.getModule());
		return "application_fee.xhtml";
	}
	
	public List<CurrencyType> getAllCurrencyType() {
		return Arrays.asList(CurrencyType.values());
	}
	
	public List<FeeType> getAllFeeType() {
		return Arrays.asList(FeeType.values());
	}
	
	public String cancel() {
		return "main.xhtml";
	}
	
	public ApplicationFee getModule() {
		return module;
	}
	
	public void setModule(ApplicationFee module) {
		this.module = module;
	}
	
	public MoneySimulation getMoneySimulation() {
		return moneySimulation;
	}

	public void setMoneySimulation(MoneySimulation moneySimulation) {
		this.moneySimulation = moneySimulation;
	}
}
