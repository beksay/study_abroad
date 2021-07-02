package org.infosystema.study_abroad.controller.docs;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.model.docs.Questions;
import org.infosystema.study_abroad.model.docs.VisaQuestion;
import org.infosystema.study_abroad.service.QuestionsService;
import org.infosystema.study_abroad.service.VisaQuestionService;
import org.infosystema.study_abroad.util.web.LoginUtil;


@Named
@ConversationScoped
public class VisaQuestionController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private QuestionsService service;
	@EJB
	private VisaQuestionService moduleService;
	@Inject
	private LoginUtil loginUtil;
	private VisaQuestion module;
	private List<Questions> list;
	private Questions questions;
	
	@PostConstruct
	public void init() {
		if (questions==null) questions= new Questions();
	}
	
	public String edit(VisaQuestion module) {
		this.module = module;		
		list = service.findByProperty("module", module);
		return "visa_question.xhtml";
	}
	
	public List<Questions> getList() {
		return list;
	}
	
	public List<Questions> getVisaList(Person person) {
		return service.findByProperty("module.person", person);
	}
	
	public String save() {
		if(questions.getId()==null) {
			questions.setDateCreated(new Date());
			questions.setUser(loginUtil.getCurrentUser());
			questions.setModule(module);
			questions = service.persist(questions);
			
			list.add(questions);
			
			if(list.isEmpty()) {
				module.setStatus(ModuleStatus.NEW);
			} else {
				module.setStatus(ModuleStatus.FILLED);
			}	
			module = moduleService.merge(module);
		}else {
			questions.setDateModify(new Date());
			questions = service.merge(questions);
			list = service.findByProperty("module", questions.getModule());
		}

		questions = new Questions();
		
		return "visa_question.xhtml";
	}
	
	public String editData(Questions questions) {
		this.questions = service.findById(questions.getId(), false);
		return "visa_question.xhtml";
	}
	
	public String delete(Questions questions) {
		
		service.remove(questions);
		
		list = service.findByProperty("module", questions.getModule());
		return "visa_question.xhtml";
	}
	
	public String cancel() {
		return "main.xhtml";
	}
	
	public VisaQuestion getModule() {
		return module;
	}
	
	public void setModule(VisaQuestion module) {
		this.module = module;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}
}
