package org.infosystema.iselect.controller.dict;

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

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.conversiation.ConversationDict;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.util.web.Messages;
import java.io.Serializable;

@Named
@ViewScoped
public class DictionaryController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private DictionaryService dictionaryService;
	@Inject
	private ConversationDict conversation;
	@Inject
	private DictionaryTypeController controller;

	private Dictionary dictionary;

	@PostConstruct
	public void init() {
		dictionary = conversation.getDictionary();
		if (dictionary == null)
			dictionary = new Dictionary();
	}

	public String add() {
		dictionary = new Dictionary();
		conversation.setDictionary(dictionary);
		return form();
	}

	public String back() {
		dictionary = new Dictionary();
		conversation.setDictionary(null);
		return "/view/dictionaries/dict_type_list.xhtml?faces-redirect=true";
	}

	public String edit(Dictionary dictionary) {
		this.dictionary = dictionary;
		conversation.setDictionary(dictionary);
		return form();
	}

	public String save() {
		if (dictionary == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}
		if (dictionary.getId() == null) {
			List<Dictionary> identifiers = dictionaryService.findByProperty("name", dictionary.getName());
			if (!identifiers.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage("form",
						new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("nameAlreadyExists"), null));
				return null;
			}
		}

		if (!FacesContext.getCurrentInstance().getMessageList().isEmpty())
			return null;
		try {
			if (dictionary.getId() == null) {
				dictionary.setType(controller.getDictionaryType());
				dictionaryService.persist(dictionary);
			} else {
				dictionaryService.merge(dictionary);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setDictionary(null);

		return cancel();

	}

	public List<Dictionary> getParentList(String query) {

		List<FilterExample> examples = new ArrayList<>();

		if (controller.getDictionaryType() != null) {
			examples.add(new FilterExample("type", controller.getDictionaryType(), InequalityConstants.EQUAL));
		} else {
			examples.add(new FilterExample("id", InequalityConstants.IS_NULL_SINGLE));
		}

		Long count = dictionaryService.countByExample(examples);

		return dictionaryService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public String delete(Dictionary c) {
		dictionaryService.remove(c);
		return cancel();
	}

	public String cancel() {
		dictionary = null;
		return list();
	}

	private String list() {
		return "/view/dictionaries/dictionary_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/dictionaries/dictionary_form.xhtml";
	}

	public Dictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}

	public DictionaryTypeController getController() {
		return controller;
	}

	public void setController(DictionaryTypeController controller) {
		this.controller = controller;
	}

}
