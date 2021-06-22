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
import org.infosystema.study_abroad.data.QuarantineObjectDataModel;
import org.infosystema.study_abroad.model.nomenclature.QuarantineObject;
import org.infosystema.study_abroad.service.QuarantineObjectService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class QuarantineObjectList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	@EJB
	private QuarantineObjectService service;
	private QuarantineObjectDataModel model; 
	private Integer first;
	private String searchString;
	private QuarantineObject quarantineObject;

	public QuarantineObjectList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){	
		List<FilterExample> filters = new ArrayList<>();
		if (searchString != null && searchString.length()>0) {
			filters.add(new FilterExample(true, "name", '%' + searchString + '%', InequalityConstants.LIKE, true));
			filters.add(new FilterExample(true, "enName", '%' + searchString + '%', InequalityConstants.LIKE, true));
			filters.add(new FilterExample(true, "kgName", '%' + searchString + '%', InequalityConstants.LIKE, true));
		}
		model = new QuarantineObjectDataModel(filters, service);	
	}
	
	public QuarantineObjectDataModel getModel() {
		return model;
	}
	
	public void setModel(QuarantineObjectDataModel model) {
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
		model = (QuarantineObjectDataModel) session.getAttribute("model");
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

	public QuarantineObject getQuarantineObject() {
		return quarantineObject;
	}

	public void setQuarantineObject(QuarantineObject quarantineObject) {
		this.quarantineObject = quarantineObject;
	}
}
