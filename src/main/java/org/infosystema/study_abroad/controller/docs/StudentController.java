package org.infosystema.study_abroad.controller.docs;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.IOUtils;
import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.beans.Message;
import org.infosystema.study_abroad.beans.SortEnum;
import org.infosystema.study_abroad.controller.BasePersonController;
import org.infosystema.study_abroad.conversiation.ConversationUser;
import org.infosystema.study_abroad.enums.ScopeConstants;
import org.infosystema.study_abroad.enums.UserStatus;
import org.infosystema.study_abroad.model.Countries;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.Mentors;
import org.infosystema.study_abroad.model.Role;
import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.model.docs.Module;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.service.CountriesService;
import org.infosystema.study_abroad.service.DictionaryService;
import org.infosystema.study_abroad.service.MentorsService;
import org.infosystema.study_abroad.service.ModuleService;
import org.infosystema.study_abroad.service.PersonService;
import org.infosystema.study_abroad.service.RoleService;
import org.infosystema.study_abroad.service.UserService;
import org.infosystema.study_abroad.util.MailSender;
import org.infosystema.study_abroad.util.PasswordBuilder;
import org.infosystema.study_abroad.util.web.FacesScopeQualifier;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.infosystema.study_abroad.util.web.Messages;
import org.infosystema.study_abroad.util.web.ScopeQualifier;
import org.primefaces.event.SelectEvent;

@Named
@RequestScoped
public class StudentController extends BasePersonController{

	private static final long serialVersionUID = 7782278859703355392L;
	@EJB
	private PersonService personService;
	@EJB
	private UserService userService;
	@EJB
	private RoleService roleService;
	@EJB
	private ModuleService moduleService;
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

	private UIComponent emailField;

	public String add() {
		conversation.setPerson(new Person());
		conversation.setUser(new User());
		return form();
	}

	public String edit(Person person) {
		conversation.setPerson(service.getByIdWithFields(person.getId(), new String[] {"languages"}));
		conversation.setUser(person.getPersonUser());
		return form();
	}

	public String save() throws Exception {

    	if(conversation.getPerson().getId()==null) {
			Person person = personService.initialize(loginUtil.getCurrentUser(),conversation.getPerson());
			ScopeQualifier qualifier = new FacesScopeQualifier();
			qualifier.setValue(PERSON_KEY, person.getId(), ScopeConstants.SESSION_SCOPE);
		}
    	
    	conversation.getPerson().setPersonUser(conversation.getUser());
        conversation.getUser().setStatus(UserStatus.ACTIVE);
		Role role = roleService.findById(3, false);
		conversation.getUser().setRole(role);
        conversation.getPerson().setCompany(loginUtil.getCurrentUser());
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		if(conversation.getPerson().getPersonUser().getId() == null ) {
			List<User> users = userService.findByProperty("email", conversation.getUser().getEmail());
	    	if(users.size()>0){
	    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "электронная почта уже существует !!!",
						"электронная почта уже существует !!!");
				FacesContext context = FacesContext.getCurrentInstance();
				context.addMessage(emailField.getClientId(context), message);
	    	}
			userService.persist(conversation.getPerson().getPersonUser());
			sendPassword(conversation.getPerson().getPersonUser());
		}else {
			userService.merge(conversation.getPerson().getPersonUser());
		}
		
		if (conversation.getPerson().getId() == null) {
			conversation.getPerson().setDateCreated(new Date());
			personService.persist(conversation.getPerson());
		} else {
			personService.merge(conversation.getPerson());
		}
    	
		return "/view/students/details/main.xhtml";
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
	
	public void onRowSelect(SelectEvent event) throws IOException {
		conversation.setPerson((Person) event.getObject());
		
		conversation.setPerson(service.getByIdWithFields(conversation.getPerson().getId(), new String[] {"languages"}));
		
		ScopeQualifier qualifier = new FacesScopeQualifier();
		qualifier.setValue(PERSON_KEY, conversation.getPerson().getId(), ScopeConstants.SESSION_SCOPE);
		
        FacesContext.getCurrentInstance().getExternalContext().redirect("/study_abroad/view/students/details/main.xhtml?cid="+conversation.getId());
        
    }
	
	public List<Module> getModules() {
		ScopeQualifier qualifier = new FacesScopeQualifier();
		Integer id = qualifier.getValue(PERSON_KEY, ScopeConstants.SESSION_SCOPE);
		List<Module> modules = qualifier.getValue(id + "_", ScopeConstants.REQUEST_SCOPE);
		
		if(modules == null) {
			List<FilterExample> list = new ArrayList<>();
			list.add(new FilterExample("person.id", id, InequalityConstants.EQUAL));
			modules = moduleService.findByExample(0, 200, SortEnum.ASCENDING, list, "index");
			
			qualifier.setValue(id + "_", modules, ScopeConstants.REQUEST_SCOPE);
		}
		
		System.out.println("modules = " + modules + " id = " + id);
		
		return modules;
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
		conversation.setUser(null);
		conversation.setPerson(null);
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

	public ConversationUser getConversation() {
		return conversation;
	}
	
	public void setConversation(ConversationUser conversation) {
		this.conversation = conversation;
	}

	public UIComponent getEmailField() {
		return emailField;
	}

	public void setEmailField(UIComponent emailField) {
		this.emailField = emailField;
	}

}
