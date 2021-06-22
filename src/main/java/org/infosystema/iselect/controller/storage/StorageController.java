package org.infosystema.iselect.controller.storage;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.iselect.model.app.Storage;
import org.infosystema.iselect.service.StorageService;
import org.infosystema.iselect.util.web.Messages;

@Named
@ViewScoped
public class StorageController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5762830281907031456L;
	@EJB
	private StorageService service;

	@Inject
	private ConversationStorage conversation;

	private Storage storage;

	@PostConstruct
	public void init() {

		storage = conversation.getStorage();
		if (storage == null)
			storage = new Storage();
	}

	public String add() {

		storage = new Storage();
		conversation.setStorage(storage);

		return form();
	}

	public String edit(Storage storage) {

		this.storage = storage;
		conversation.setStorage(storage);

		return form();
	}

	public String save() {

		if (storage == null) {

			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}

		if (storage.getId() == null) {

			service.persist(storage);

		} else {

			service.merge(storage);
		}

		return cancel();
	}

	public String cancel() {

		storage = null;
		conversation.setStorage(null);

		return list();
	}

	private String list() {
		return "/view/storage/storage_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/storage/storage_form.xhtml";
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

}
