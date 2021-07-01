package org.infosystema.study_abroad.controller.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.data.PersonDataModel;
import org.infosystema.study_abroad.model.docs.Person;
import org.infosystema.study_abroad.service.PersonService;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class StudentsList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	@EJB
	private PersonService service;
	private PersonDataModel model; 
	@Inject
	private LoginUtil loginUtil;
	private Integer first;
	private String searchString;
	private Person person;

	public StudentsList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){	
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("company", loginUtil.getCurrentUser(), InequalityConstants.EQUAL));
		if (searchString != null && searchString.length()>0) {
			filters.add(new FilterExample(true, "firstname", '%' + searchString + '%', InequalityConstants.LIKE, true));
			filters.add(new FilterExample(true, "lastname", '%' + searchString + '%', InequalityConstants.LIKE, true));
			filters.add(new FilterExample(true, "patronymic", '%' + searchString + '%', InequalityConstants.LIKE, true));
		}
		model = new PersonDataModel(filters, service);	
	}
	
	public PersonDataModel getModel() {
		return model;
	}
	
	public void setModel(PersonDataModel model) {
		this.model = model;
	}
	
	public Integer getFirst() {
		return first;
	}
	
	public void setFirst(Integer first) {
		this.first = first;
	}
	
	public void saveState() {
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("model", model);
		session.setAttribute("first", first);
	}
	
	public void restoreState() {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		model = (PersonDataModel) session.getAttribute("model");
		first = (Integer) session.getAttribute("first");
	}
	
	public void removeState() {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("model", null);
		session.setAttribute("first", null);
		
		model = null;
		first = null;
	}
	
	public void onPageChange(PageEvent event) {  
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		HttpSession session = request.getSession();
		setFirst(((DataTable) event.getSource()).getRows() * event.getPage());
		session.setAttribute("first", first);
	}


	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}


}
