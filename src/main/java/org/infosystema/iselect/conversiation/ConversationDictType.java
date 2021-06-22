package org.infosystema.iselect.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.model.DictionaryType;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationDictType extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private DictionaryType dictionaryType;

	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	}

	
}
