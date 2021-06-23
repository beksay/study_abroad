package org.infosystema.study_abroad.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.study_abroad.annotation.Logged;
import org.infosystema.study_abroad.model.Universities;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationUniversities extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Universities universities;

	public Universities getUniversities() {
		return universities;
	}

	public void setUniversities(Universities universities) {
		this.universities = universities;
	}

}
