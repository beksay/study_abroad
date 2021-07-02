package org.infosystema.study_abroad.controller.docs;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.docs.EmbassyAppointment;
import org.infosystema.study_abroad.service.EmbassyAppointmentService;
import org.infosystema.study_abroad.util.web.LoginUtil;


@Named
@ConversationScoped
public class EmbassyAppointmentController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private EmbassyAppointmentService moduleService;
	@Inject
	private LoginUtil loginUtil;

	private EmbassyAppointment module;

	@PostConstruct
	public void init() {
		// Empty
	}

	public String edit(EmbassyAppointment module) {
		module = moduleService.findById(module.getId(), false);
		this.setModule(module);
		return "embassy_appointment.xhtml";
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

	public EmbassyAppointment getModule() {
		return module;
	}
	
	public void setModule(EmbassyAppointment module) {
		this.module = module;
	}

}
