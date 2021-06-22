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
import org.infosystema.iselect.data.DisinfectionDataModel;
import org.infosystema.iselect.model.app.Disinfection;
import org.infosystema.iselect.service.DisinfectionService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class DisinfectionList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	@EJB
	private DisinfectionService service;
	private DisinfectionDataModel model; 
	private Integer first;
	private Disinfection Disinfection;
	private String actNumber;

	public DisinfectionList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){	
		List<FilterExample> filters = new ArrayList<>();
		if (actNumber != null && actNumber.length()>0) filters.add(new FilterExample("inspection.actNumber", '%' + actNumber + '%', InequalityConstants.LIKE));
		model = new DisinfectionDataModel(filters, service);	
	}
	
	public void clearData() {
		init();
	}
	
	public DisinfectionDataModel getModel() {
		return model;
	}
	
	public void setModel(DisinfectionDataModel model) {
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
		model = (DisinfectionDataModel) session.getAttribute("model");
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

	public Disinfection getDisinfection() {
		return Disinfection;
	}
	
	public void setDisinfection(Disinfection Disinfection) {
		this.Disinfection = Disinfection;
	}

	public String getActNumber() {
		return actNumber;
	}

	public void setActNumber(String actNumber) {
		this.actNumber = actNumber;
	}
}
