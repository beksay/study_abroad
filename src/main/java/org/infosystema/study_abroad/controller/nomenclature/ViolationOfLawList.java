package org.infosystema.study_abroad.controller.nomenclature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.InequalityConstants;
import org.infosystema.study_abroad.data.ViolationOfLawDataModel;
import org.infosystema.study_abroad.model.nomenclature.ViolationOfLaw;
import org.infosystema.study_abroad.service.ViolationOfLawService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class ViolationOfLawList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	@EJB
	private ViolationOfLawService service;
	private ViolationOfLawDataModel model; 
	private Integer first;
	private String searchString;
	private ViolationOfLaw violationOfLaw;

	public ViolationOfLawList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){	
		List<FilterExample> filters = new ArrayList<>();
		if (searchString != null && searchString.length()>0) {
			filters.add(new FilterExample("violationName", '%' + searchString + '%', InequalityConstants.LIKE));
		}
		model = new ViolationOfLawDataModel(filters, service);	
	}
	
	public ViolationOfLawDataModel getModel() {
		return model;
	}
	
	public void setModel(ViolationOfLawDataModel model) {
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
		model = (ViolationOfLawDataModel) session.getAttribute("model");
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

	public ViolationOfLaw getViolationOfLaw() {
		return violationOfLaw;
	}

	public void setViolationOfLaw(ViolationOfLaw violationOfLaw) {
		this.violationOfLaw = violationOfLaw;
	}
}
