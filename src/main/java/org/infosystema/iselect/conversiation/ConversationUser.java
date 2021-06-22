package org.infosystema.iselect.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.model.User;
import org.infosystema.iselect.model.UserLog;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationUser extends Conversational {

	private static final long serialVersionUID = -6100072166946495229L;

	private User user;
	private UserLog logUser;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserLog getLogUser() {
		return logUser;
	}

	public void setLogUser(UserLog logUser) {
		this.logUser = logUser;
	}

}
