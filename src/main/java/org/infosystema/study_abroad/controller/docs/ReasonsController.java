package org.infosystema.study_abroad.controller.docs;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.docs.Reasons;
import org.infosystema.study_abroad.service.ReasonsService;
import org.infosystema.study_abroad.util.web.LoginUtil;


@Named
@ConversationScoped
public class ReasonsController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private ReasonsService moduleService;
	@Inject
	private LoginUtil loginUtil;

	private Reasons module;

	@PostConstruct
	public void init() {
		// Empty
	}

	public String edit(Reasons module) {
		module = moduleService.findById(module.getId(), false);
		this.setModule(module);
		return "reasons.xhtml";
	}

	public String save() {
		module.setUser(loginUtil.getCurrentUser());
        module.setDateCreated(new Date());
		if (module.getId() == null) {
			module.setStatus(ModuleStatus.NEW);
		} else {
			module.setStatus(ModuleStatus.FILLED);
		}

		module = moduleService.merge(module);

		return "main.xhtml";
	}

	public String cancel() {
		return "main.xhtml";
	}

	public Reasons getModule() {
		return module;
	}
	
	public void setModule(Reasons module) {
		this.module = module;
	}
}
