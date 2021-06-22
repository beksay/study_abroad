package org.infosystema.study_abroad.controller.dict;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.infosystema.study_abroad.annotation.Logged;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.model.DictionaryType;
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
		FacesContext.getCurrentInstance().getExternalContext().redirect("/study_abroad/view/dictionaries/dictionary_list.xhtml?cid=" + getId());
	}

	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

}
