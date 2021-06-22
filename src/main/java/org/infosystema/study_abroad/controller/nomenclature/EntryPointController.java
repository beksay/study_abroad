package org.infosystema.study_abroad.controller.nomenclature;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.conversiation.ConversationEntryPoint;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.nomenclature.EntryPoint;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.EntryPointService;
import org.infosystema.study_abroad.util.web.Messages;

import java.io.Serializable;

@Named
@ViewScoped
public class EntryPointController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3764034233439264209L;
	@EJB
	private EntryPointService service;
	@EJB
	private DictionaryService dictionaryService;
	@Inject
	private ConversationEntryPoint conversation;

	private EntryPoint entryPoint;

	@PostConstruct
	public void init() {
		entryPoint = conversation.getEntryPoint();
		if (entryPoint == null)
			entryPoint = new EntryPoint();
	}

	public String add() {
		entryPoint = new EntryPoint();
		conversation.setEntryPoint(entryPoint);
		return form();
	}

	public String edit(EntryPoint entryPoint) {
		this.entryPoint = entryPoint;
		conversation.setEntryPoint(entryPoint);
		return form();
	}

	public String save() {
		if (entryPoint == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}

		if (!FacesContext.getCurrentInstance().getMessageList().isEmpty())
			return null;
		try {
			if (entryPoint.getId() == null) {
				service.persist(entryPoint);
			} else {
				service.merge(entryPoint);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setEntryPoint(null);

		return cancel();

	}

	public List<Dictionary> getWorldClassifierList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 2, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictionaryService.countByExample(examples);

		return dictionaryService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<Dictionary> getTransportationMethodList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 15, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		return dictionaryService.findByExample(0, 100, examples);
	}

	public String delete(EntryPoint c) {
		service.remove(c);
		return cancel();
	}

	public String cancel() {
		entryPoint = null;
		return list();
	}

	private String list() {
		return "/view/nomenclature/entry_point_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/nomenclature/entry_point_form.xhtml";
	}

	public EntryPoint getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(EntryPoint entryPoint) {
		this.entryPoint = entryPoint;
	}

}
