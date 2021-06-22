package org.infosystema.iselect.controller.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.beans.Message;
import org.infosystema.iselect.beans.SortEnum;
import org.infosystema.iselect.conversiation.ConversationUser;
import org.infosystema.iselect.enums.UserOperations;
import org.infosystema.iselect.enums.UserStatus;
import org.infosystema.iselect.model.Role;
import org.infosystema.iselect.model.Subdivision;
import org.infosystema.iselect.model.User;
import org.infosystema.iselect.model.UserLog;
import org.infosystema.iselect.service.RoleService;
import org.infosystema.iselect.service.SubdivisionService;
import org.infosystema.iselect.service.UserLogService;
import org.infosystema.iselect.service.UserService;
import org.infosystema.iselect.util.MailSender;
import org.infosystema.iselect.util.PasswordBuilder;
import org.infosystema.iselect.util.web.LoginUtil;
import org.infosystema.iselect.util.web.Messages;

@Named
@ViewScoped
public class UserAction implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7782278859703355392L;
	@EJB
	private UserService service;
	@EJB
	private UserLogService userLogService;
	@EJB
	private SubdivisionService subdivisionService;
	@EJB
	private RoleService roleService;
	@Inject
	private ConversationUser conversation;
	@Inject
	private LoginUtil loginUtil;

	private User user;

	private boolean isDataChanged;

	@PostConstruct
	public void init() {
		user = conversation.getUser();
		if (user == null)
			user = new User();

		isDataChanged = false;
	}

	public String add() {
		user = new User();
		conversation.setUser(user);

		return form();
	}

	public String edit(User user) {
		this.user = user;
		conversation.setUser(user);

		UserLog userLog = new UserLog();
		userLog.setOldRole(conversation.getUser().getRole());
		userLog.setOldUsername(conversation.getUser().getUsername());
		userLog.setOldStatus(conversation.getUser().getStatus());
		userLog.setOldSubdivision(conversation.getUser().getSubdivision());
		userLog.setOldCodes(conversation.getUser().getCodes());
		userLog.setOldInn(conversation.getUser().getInn());
		userLog.setOldFullname(conversation.getUser().getFullname());
		userLog.setOldEmail(conversation.getUser().getEmail());
		userLog.setOldPosition(conversation.getUser().getPosition());

		conversation.setLogUser(userLog);

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
				logging(user);

			} else {

				service.merge(user);
				loggingEdit(user);
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

		List<User> users = service.findByProperty("username", user.getUsername());
		if (users.isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("login-form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("usernameIsIncorrect"), null));
			return null;
		}

		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("username", user.getUsername(), InequalityConstants.EQUAL, true));
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

		String body = MessageFormat.format(template, user.getUsername(), user.getEmail(), theUser.getFullname(),
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

	private void logging(User user) {

		UserLog log = new UserLog();
		log.setUserInfo(user);
		log.setUserChanged(loginUtil.getCurrentUser());
		log.setAction(UserOperations.USER_CREATED);
		log.setDateChanged(new Date());
		log.setOldRole(user.getRole());
		log.setOldUsername(user.getUsername());
		log.setOldStatus(user.getStatus());
		log.setOldSubdivision(user.getSubdivision());
		log.setOldCodes(user.getCodes());
		log.setOldInn(user.getInn());
		log.setOldFullname(user.getFullname());
		log.setOldEmail(user.getEmail());
		log.setOldPosition(user.getPosition());

		if (log.getId() == null) {

			userLogService.persist(log);
		}
	}

	private void loggingEdit(User user) {

		if (!conversation.getLogUser().getOldRole().equals(user.getRole())) {

			conversation.getLogUser().setNewRole(user.getRole());
			isDataChanged = true;
		}

		if (!conversation.getLogUser().getOldUsername().equals(user.getUsername())) {

			conversation.getLogUser().setNewUsername(user.getUsername());
			isDataChanged = true;
		}

		if (!conversation.getLogUser().getOldStatus().equals(user.getStatus())) {

			conversation.getLogUser().setNewStatus(user.getStatus());
			isDataChanged = true;
		}

		if (!conversation.getLogUser().getOldSubdivision().equals(user.getSubdivision())) {

			conversation.getLogUser().setOldSubdivision(user.getSubdivision());
			isDataChanged = true;
		}

		if (!conversation.getLogUser().getOldCodes().equals(user.getCodes())) {

			conversation.getLogUser().setNewCodes(user.getCodes());
			isDataChanged = true;
		}

		if (!conversation.getLogUser().getOldInn().equals(user.getInn())) {

			conversation.getLogUser().setNewInn(user.getInn());
			isDataChanged = true;
		}

		if (!conversation.getLogUser().getOldFullname().equals(user.getFullname())) {

			conversation.getLogUser().setNewFullname(user.getFullname());
			isDataChanged = true;
		}

		if (!conversation.getLogUser().getOldEmail().equals(user.getEmail())) {

			conversation.getLogUser().setNewEmail(user.getEmail());
			isDataChanged = true;
		}

		if (!conversation.getLogUser().getOldPosition().equals(user.getPosition())) {

			conversation.getLogUser().setNewPosition(user.getPosition());
			isDataChanged = true;
		}

		if (Boolean.TRUE.equals(isDataChanged) && conversation.getLogUser().getId() == null) {

			conversation.getLogUser().setUserChanged(loginUtil.getCurrentUser());
			conversation.getLogUser().setUserInfo(user);
			conversation.getLogUser().setAction(UserOperations.USER_CHANGED);
			conversation.getLogUser().setDateChanged(new Date());

			userLogService.persist(conversation.getLogUser());
		}
	}

	public List<Subdivision> getSubdivisionList() {
		List<FilterExample> examples = new ArrayList<>();
		if (user.getRole() != null && user.getRole().getId() == 4) {
			examples.add(new FilterExample("parent.code", "00", InequalityConstants.EQUAL));
		} else {
			examples.add(new FilterExample("parent.code", Arrays.asList("1", "00"), InequalityConstants.NOT_IN));
		}
		return subdivisionService.findByExample(0, 100, examples);
	}

	public List<Role> getRoleList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("id", 1, InequalityConstants.NOT_EQUAL));
		return roleService.findByExample(0, 100, examples);
	}

	public List<UserLog> getLogList() {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("userInfo", user, InequalityConstants.EQUAL));

		Long count = userLogService.countByExample(examples);

		return userLogService.findByExample(0, Math.toIntExact(count), SortEnum.DESCENDING, examples, "id");
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
