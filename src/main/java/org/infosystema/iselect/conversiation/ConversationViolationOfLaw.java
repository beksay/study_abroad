package org.infosystema.iselect.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.model.nomenclature.ViolationOfLaw;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationViolationOfLaw extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private ViolationOfLaw violationOfLaw;

	public ViolationOfLaw getViolationOfLaw() {
		return violationOfLaw;
	}

	public void setViolationOfLaw(ViolationOfLaw violationOfLaw) {
		this.violationOfLaw = violationOfLaw;
	}

}
