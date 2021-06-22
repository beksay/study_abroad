package org.infosystema.iselect.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.model.nomenclature.QuarantineObject;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationQuarantineObject extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private QuarantineObject quarantineObject;

	public QuarantineObject getQuarantineObject() {
		return quarantineObject;
	}

	public void setQuarantineObject(QuarantineObject quarantineObject) {
		this.quarantineObject = quarantineObject;
	}
	
}
