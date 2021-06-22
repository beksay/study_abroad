package org.infosystema.study_abroad.controller.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.infosystema.study_abroad.data.ApplicationsDataModel;
import org.infosystema.study_abroad.enums.AppStatus;
import org.infosystema.study_abroad.enums.Applicant;
import org.infosystema.study_abroad.enums.MemberType;
import org.infosystema.study_abroad.model.app.Applications;
import org.infosystema.study_abroad.service.ApplicationsService;
import org.infosystema.study_abroad.util.web.LoginUtil;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class ApplicationsList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	@EJB
	private ApplicationsService service;
	@Inject
	private LoginUtil loginUtil;
	private ApplicationsDataModel model; 
	private Integer first;
	private String inn;
	private String number;
	private MemberType type;
	private Applicant applicant;
	private Applications applications;

	public ApplicationsList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){	
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("status",Arrays.asList(AppStatus.SENDED, AppStatus.ACCEPTED,AppStatus.DECLINED) , InequalityConstants.IN));
		filters.add(new FilterExample("subdivision", loginUtil.getCurrentUser().getSubdivision(), InequalityConstants.EQUAL));
		if (inn != null && inn.length()>0) filters.add(new FilterExample("inn", '%' + inn + '%', InequalityConstants.LIKE));
		if (number != null && number.length()>0) filters.add(new FilterExample("number", '%' + number + '%', InequalityConstants.LIKE));
		if (type != null) filters.add(new FilterExample("type", type, InequalityConstants.EQUAL));
		if (applicant != null && number.length()>0) filters.add(new FilterExample("applicant", applicant, InequalityConstants.EQUAL));
		model = new ApplicationsDataModel(filters, service);	
	}
	
	public void clearData() {
		inn = null;
		number = null;
		type = null;
		applicant = null;
		init();
	}
	
	public ApplicationsDataModel getModel() {
		return model;
	}
	
	public void setModel(ApplicationsDataModel model) {
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
		model = (ApplicationsDataModel) session.getAttribute("model");
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

	public Applications getApplications() {
		return applications;
	}
	
	public void setApplications(Applications applications) {
		this.applications = applications;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public MemberType getType() {
		return type;
	}

	public void setType(MemberType type) {
		this.type = type;
	}

	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}
}
