package org.infosystema.study_abroad.controller.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.beans.Message;
import org.infosystema.study_abroad.beans.SortEnum;
import org.infosystema.study_abroad.conversiation.ConversationUser;
import org.infosystema.study_abroad.enums.UserStatus;
import org.infosystema.study_abroad.model.Role;
import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.service.RoleService;
import org.infosystema.study_abroad.service.UserService;
import org.infosystema.study_abroad.util.MailSender;
import org.infosystema.study_abroad.util.PasswordBuilder;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ViewScoped
public class UserAction implements Serializable {

	private static final long serialVersionUID = 7782278859703355392L;
	@EJB
	private UserService service;
	@EJB
	private RoleService roleService;
	@Inject
	private ConversationUser conversation;
	@Inject
	private LoginUtil loginUtil;

	private User user;

	@PostConstruct
	public void init() {
		user = conversation.getUser();
		if (user == null)
			user = new User();
	}

	public String add() {
		user = new User();
		conversation.setUser(user);

		return form();
	}

	public String edit(User user) {
		this.user = user;
		conversation.setUser(user);
		return form();
	}

	public String save() {
		if (user == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}
		user.setStatus(UserStatus.ACTIVE);
		if (!FacesContext.getCurrentInstance().getMessageList().isEmpty())
			return null;
		try {
			if (user.getId() == null) {

				service.persist(user);
				sendPassword(user);
			} else {
				service.merge(user);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setUser(null);
		return cancel();
	}

	public String sendPassword(User user) throws Exception {

		List<User> users = service.findByProperty("email", user.getEmail());
		if (users.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("login-form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("usernameIsIncorrect"), null));
			return null;
		}

		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("email", user.getEmail(), InequalityConstants.EQUAL));

		List<User> userList = service.findByExample(0, 1, SortEnum.ASCENDING, examples, "id");

		if (userList.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("login-form", new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getMessage("usernameOrEmailIncorrect"), null));
			return null;
		}

		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("user.template");
		String template = null;
		try {
			template = IOUtils.toString(stream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		User theUser = userList.get(0);
		theUser = service.findByProperty("id", theUser.getId()).get(0);

		PasswordBuilder builder = new PasswordBuilder();
		builder.lowercase(2).uppercase(8).specials(2).digits(2).shuffle();

		String thePassword = builder.build();

		String body = MessageFormat.format(template, user.getEmail(), user.getEmail(), theUser.getEmail(),
				thePassword);

		theUser.setPassword(loginUtil.getHashPassword(thePassword));
		theUser.setStatus(UserStatus.ACTIVE);
		service.merge(theUser);

		Message message = new Message();
		message.setEmail(user.getEmail());
		message.setSubject("АСУП");
		message.setContent(body);

		MailSender.getInstance().asyncSend(message);

		FacesContext.getCurrentInstance().addMessage("login-form",
				new FacesMessage(FacesMessage.SEVERITY_INFO, Messages.getMessage("messagesSend"), null));

		return null;
	}

	public List<Role> getRoleList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("id", 1, InequalityConstants.NOT_EQUAL));
		return roleService.findByExample(0, 100, examples);
	}

	public String delete(User c) {
		service.remove(c);
		return cancel();
	}

	public String cancel() {
		user = null;
		return list();
	}

	private String list() {
		return "/view/user/list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/user/form.xhtml";
	}
	
	public String home() {
		return "/view/main.xhtml";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
