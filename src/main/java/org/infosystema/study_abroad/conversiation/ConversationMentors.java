package org.infosystema.study_abroad.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.study_abroad.annotation.Logged;
import org.infosystema.study_abroad.model.Mentors;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationMentors extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Mentors mentors;

	public Mentors getMentors() {
		return mentors;
	}

	public void setMentors(Mentors mentors) {
		this.mentors = mentors;
	}
}
