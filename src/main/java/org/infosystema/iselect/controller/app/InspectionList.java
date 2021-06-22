package org.infosystema.iselect.controller.app;

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

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.data.InspectionDataModel;
import org.infosystema.iselect.model.app.Inspection;
import org.infosystema.iselect.service.InspectionService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class InspectionList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	@EJB
	private InspectionService service;
	private InspectionDataModel model; 
	private Integer first;
	private String actNumber;
	private Inspection Inspection;

	public InspectionList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){	
		List<FilterExample> filters = new ArrayList<>();
		if (actNumber != null && actNumber.length()>0) filters.add(new FilterExample("actNumber", '%' + actNumber + '%', InequalityConstants.LIKE));
		model = new InspectionDataModel(filters, service);	
	}
	
	public void clearData() {
		actNumber = null;
		init();
	}
	
	public InspectionDataModel getModel() {
		return model;
	}
	
	public void setModel(InspectionDataModel model) {
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
		model = (InspectionDataModel) session.getAttribute("model");
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

	public Inspection getInspection() {
		return Inspection;
	}
	
	public void setInspection(Inspection Inspection) {
		this.Inspection = Inspection;
	}
	
	public String getActNumber() {
		return actNumber;
	}
	
	public void setActNumber(String actNumber) {
		this.actNumber = actNumber;
	}
}
