package org.infosystema.study_abroad.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.study_abroad.annotation.Logged;
import org.infosystema.study_abroad.model.nomenclature.EntryPoint;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationEntryPoint extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private EntryPoint entryPoint;

	public EntryPoint getEntryPoint() {
		return entryPoint;
	}

	public void setEntryPoint(EntryPoint entryPoint) {
		this.entryPoint = entryPoint;
	}

	

	
}
