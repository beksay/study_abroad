package org.infosystema.iselect.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.iselect.annotation.RolesAllowed;
import org.infosystema.iselect.enums.ScopeConstants;
import org.infosystema.iselect.model.User;
import org.infosystema.iselect.service.UserService;
import org.infosystema.iselect.util.web.FacesMessages;
import org.infosystema.iselect.util.web.FacesScopeQualifier;
import org.infosystema.iselect.util.web.LoginUtil;
import org.infosystema.iselect.util.web.Messages;
import org.infosystema.iselect.util.web.ScopeQualifier;

/**
 * 
 * @author Akzholbek Omorov
 *
 */

@Named
@ConversationScoped
@RolesAllowed(roles = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
public class PasswordChanger implements Serializable {

	private static final long serialVersionUID = 5651758429305872940L;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	@EJB
	private UserService service;

	@Inject
	private Conversation conversation;
	@Inject
	private LoginUtil loginUtil;

	@PostConstruct
	public void initialize() {
		if (conversation.isTransient())
			conversation.begin();
	}

	public void closeConversation() {
		if (!conversation.isTransient())
			conversation.end();
	}

	public String change() {

		return "/view/user/change_password.xhtml?faces-redirect=true";
	}

	public String cancel() {
		closeConversation();
		return "/view/main.xhtml?faces-redirect=true";
	}

	public String doChange() throws Exception {
		User user = new FacesScopeQualifier().getValue(LoginUtil.CURRENT_USER_SESSION_KEY,
				ScopeConstants.SESSION_SCOPE);

		String hashPassword = loginUtil.getHashPassword(newPassword);

		if (newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
			FacesMessages.addMessage(Messages.getMessage("passwordNotMatch"), "", null);
			return null;
		}

		if (oldPassword == null || !user.getPassword().equals(loginUtil.getHashPassword(oldPassword))) {
			FacesMessages.addMessage(Messages.getMessage("invalidPassword"), "", null);
			return null;
		}

		user.setPassword(hashPassword);
		service.merge(user);

		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.remove("changePassword", ScopeConstants.SESSION_SCOPE);

		return cancel();
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
