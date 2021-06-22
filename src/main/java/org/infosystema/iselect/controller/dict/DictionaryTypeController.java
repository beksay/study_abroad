package org.infosystema.iselect.controller.dict;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.model.DictionaryType;
import org.primefaces.event.SelectEvent;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Logged
@Named
@ConversationScoped
public class DictionaryTypeController extends Conversational {

	private static final long serialVersionUID = 1L;

	private DictionaryType dictionaryType;

	@PostConstruct
	public void init() {
		dictionaryType = new DictionaryType();
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		dictionaryType = (DictionaryType) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/iselect/view/dictionaries/dictionary_list.xhtml?cid=" + getId());
	}

	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

}
