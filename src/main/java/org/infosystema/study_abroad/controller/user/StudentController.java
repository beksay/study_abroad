package org.infosystema.study_abroad.controller.user;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.beans.Message;
import org.infosystema.study_abroad.conversiation.ConversationUser;
import org.infosystema.study_abroad.enums.UserStatus;
import org.infosystema.study_abroad.model.Countries;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.Mentors;
import org.infosystema.study_abroad.model.Person;
import org.infosystema.study_abroad.model.Role;
import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.service.CountriesService;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.MentorsService;
import org.infosystema.study_abroad.service.PersonService;
import org.infosystema.study_abroad.service.RoleService;
import org.infosystema.study_abroad.service.UserService;
import org.infosystema.study_abroad.util.MailSender;
import org.infosystema.study_abroad.util.PasswordBuilder;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.infosystema.study_abroad.util.web.Messages;

@Named
@ViewScoped
public class StudentController implements Serializable {

	private static final long serialVersionUID = 7782278859703355392L;
	@EJB
	private PersonService personService;
	@EJB
	private UserService userService;
	@EJB
	private RoleService roleService;
	@EJB
	private CountriesService countriesService;
	@EJB
	private MentorsService mentorService;
	@EJB
	private DictionaryService dictService;
	@Inject
	private ConversationUser conversation;
	@Inject
	private LoginUtil loginUtil;

	private User user;
	private Person person; 
	private UIComponent emailField;

	@PostConstruct
	public void init() {
		person=conversation.getPerson();
		if (person==null) person= new Person();
		user = conversation.getUser();
		if (user == null) user = new User();
	}

	public String add() {
		person = new Person();
		conversation.setPerson(person);
		return form();
	}

	public String edit(Person person) {
		this.setUser(person.getPersonUser());
		this.person = person;
		conversation.setUser(user);
		conversation.setPerson(person);
		return form();
	}

	public String save() throws Exception {
		List<User> users = userService.findByProperty("email", user.getEmail());
    	if(users.size()>0){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "электронная почта уже существует !!!",
					"электронная почта уже существует !!!");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(emailField.getClientId(context), message);
    	}
    	
    	person.setPersonUser(user);  
		user.setStatus(UserStatus.ACTIVE);
		Role role = roleService.findById(3, false);
		user.setRole(role);
		person.setCompany(loginUtil.getCurrentUser());
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		if(person.getPersonUser().getId() == null ) {
			userService.persist(person.getPersonUser());
			sendPassword(person.getPersonUser());
		}else {
			userService.merge(person.getPersonUser());
		}
		
		if (person.getId() == null) {
			person.setDateCreated(new Date());
			personService.persist(person);
		} else {
			personService.merge(person);
		}
		
		conversation.setUser(null);
		conversation.setPerson(null);
    	
		return cancel();
	}

	public String sendPassword(User user) throws Exception {

		InputStream stream = this.getClass().getClassLoader().getResourceAsStream("user.template");
		String template = null;
		try {
			template = IOUtils.toString(stream, StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}

		User theUser = user;
		theUser = userService.findByProperty("id", theUser.getId()).get(0);

		PasswordBuilder builder = new PasswordBuilder();
		builder.lowercase(2).uppercase(8).specials(2).digits(2).shuffle();

		String thePassword = builder.build();

		String body = MessageFormat.format(template, user.getEmail(), user.getCompanyName(), theUser.getEmail(),
				thePassword);

		theUser.setPassword(loginUtil.getHashPassword(thePassword));
		theUser.setStatus(UserStatus.ACTIVE);
		userService.merge(theUser);

		Message message = new Message();
		message.setEmail(user.getEmail());
		message.setSubject("Study Abroad");
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
	
	public List<Countries> getCountryList(String query) {

		List<FilterExample> examples = new ArrayList<>();
		Long count = countriesService.countByExample(examples);

		return countriesService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}
	
	public List<Mentors> getMentorsList(String query) {

		List<FilterExample> examples = new ArrayList<>();
		Long count = mentorService.countByExample(examples);

		return mentorService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getFullname().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}
	
	public List<Dictionary> getEnglishLevelList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 1, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictService.countByExample(examples);
		return dictService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public String delete(Person c) {
		personService.remove(c);
		return cancel();
	}

	public String cancel() {
		user = null;
		return list();
	}

	private String list() {
		return "/view/students/student_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/students/student_form.xhtml";
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
	
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}

	public UIComponent getEmailField() {
		return emailField;
	}

	public void setEmailField(UIComponent emailField) {
		this.emailField = emailField;
	}

}
