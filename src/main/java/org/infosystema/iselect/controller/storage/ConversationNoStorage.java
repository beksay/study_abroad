package org.infosystema.iselect.controller.storage;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.model.app.NoStorage;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationNoStorage extends Conversational {

	private static final long serialVersionUID = -6100072166946495229L;

	private NoStorage noStorage;

	public NoStorage getNoStorage() {
		return noStorage;
	}

	public void setNoStorage(NoStorage noStorage) {
		this.noStorage = noStorage;
	}

}
