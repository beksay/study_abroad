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
import org.infosystema.iselect.data.ExpertisesDataModel;
import org.infosystema.iselect.model.app.Expertises;
import org.infosystema.iselect.service.ExpertisesService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class ExpertisesList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	@EJB
	private ExpertisesService service;
	private ExpertisesDataModel model; 
	private Integer first;
	private String samplingLocation;
	private Expertises Expertises;

	public ExpertisesList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){	
		List<FilterExample> filters = new ArrayList<>();
		if (samplingLocation != null && samplingLocation.length()>0) filters.add(new FilterExample("samplingLocation", '%' + samplingLocation + '%', InequalityConstants.LIKE));
		model = new ExpertisesDataModel(filters, service);	
	}
	
	public void clearData() {
		samplingLocation = null;
		init();
	}
	
	public ExpertisesDataModel getModel() {
		return model;
	}
	
	public void setModel(ExpertisesDataModel model) {
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
		model = (ExpertisesDataModel) session.getAttribute("model");
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

	public Expertises getExpertises() {
		return Expertises;
	}
	
	public void setExpertises(Expertises Expertises) {
		this.Expertises = Expertises;
	}
	
	public String getSamplingLocation() {
		return samplingLocation;
	}
	
	public void setSamplingLocation(String samplingLocation) {
		this.samplingLocation = samplingLocation;
	}
}
