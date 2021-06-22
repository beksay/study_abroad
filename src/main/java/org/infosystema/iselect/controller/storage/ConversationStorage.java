package org.infosystema.iselect.controller.storage;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.model.app.Storage;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationStorage extends Conversational {

	private static final long serialVersionUID = -6100072166946495229L;

	private Storage storage;

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

}
