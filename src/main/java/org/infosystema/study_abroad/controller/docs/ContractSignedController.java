package org.infosystema.study_abroad.controller.docs;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.SystemException;

import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.controller.FileUploadController;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.Attachment;
import org.infosystema.study_abroad.model.docs.ContractSigned;
import org.infosystema.study_abroad.service.ContractSignedService;
import org.infosystema.study_abroad.util.Util;
import org.infosystema.study_abroad.util.web.LoginUtil;


@Named
@ConversationScoped
public class ContractSignedController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private ContractSignedService moduleService;
	@Inject
	private FileUploadController uploadController;
	@Inject
	private LoginUtil loginUtil;

	private ContractSigned module;

	@PostConstruct
	public void init() {
		// Empty
	}

	public String edit(ContractSigned module) {
		module = moduleService.getByIdWithFields(module.getId(), new String[]{"attachments"});
		this.setModule(module);
		uploadController.setFiles(Util.getFiles(module.getAttachments()));
		return "contract_signed.xhtml";
	}

	public String save() {
		
		Set<Attachment> attachments = new HashSet<>();
		try {
			attachments.addAll(uploadController.convert());
		} catch (SystemException e) {
			e.printStackTrace();
		}
		module.setAttachments(attachments);
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

	public ContractSigned getModule() {
		return module;
	}
	
	public void setModule(ContractSigned module) {
		this.module = module;
	}

}
