package org.infosystema.iselect.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.model.nomenclature.Organization;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationOrganization extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Organization organization;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
}
