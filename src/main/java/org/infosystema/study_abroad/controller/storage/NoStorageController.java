package org.infosystema.study_abroad.controller.storage;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.model.app.NoStorage;
import org.infosystema.study_abroad.service.NoStorageService;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ViewScoped
public class NoStorageController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5762830281907031456L;
	@EJB
	private NoStorageService service;

	@Inject
	private ConversationNoStorage conversation;

	private NoStorage noStorage;

	@PostConstruct
	public void init() {

		noStorage = conversation.getNoStorage();
		if (noStorage == null)
			noStorage = new NoStorage();
	}

	public String add() {

		noStorage = new NoStorage();
		conversation.setNoStorage(noStorage);

		return form();
	}

	public String edit(NoStorage noStorage) {

		this.noStorage = noStorage;
		conversation.setNoStorage(noStorage);

		return form();
	}

	public String save() {

		if (noStorage == null) {

			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}

		if (noStorage.getId() == null) {

			service.persist(noStorage);

		} else {

			service.merge(noStorage);
		}

		return cancel();
	}

	public String cancel() {

		noStorage = null;
		conversation.setNoStorage(null);

		return list();
	}

	private String list() {
		return "/view/no_storage/no_storage_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/no_storage/no_storage_form.xhtml";
	}

	public NoStorage getNoStorage() {
		return noStorage;
	}

	public void setNoStorage(NoStorage noStorage) {
		this.noStorage = noStorage;
	}

}
