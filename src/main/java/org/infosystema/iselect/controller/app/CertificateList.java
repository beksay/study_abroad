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
import org.infosystema.iselect.data.CertificateDataModel;
import org.infosystema.iselect.enums.CertificateStatus;
import org.infosystema.iselect.enums.InspectionStatus;
import org.infosystema.iselect.model.app.Certificate;
import org.infosystema.iselect.service.CertificateService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class CertificateList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	@EJB
	private CertificateService service;
	private CertificateDataModel model; 
	private Integer first;
	private Certificate Certificate;
	private String actNumber;

	public CertificateList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){	
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("inspection.status", InspectionStatus.SENDED_TO_CERTIFICATE, InequalityConstants.EQUAL));
		if (actNumber != null && actNumber.length()>0) filters.add(new FilterExample("inspection.actNumber", '%' + actNumber + '%', InequalityConstants.LIKE));
		model = new CertificateDataModel(filters, service);	
	}
	
	public void clearData() {
		init();
	}
	
	public CertificateDataModel getModel() {
		return model;
	}
	
	public void setModel(CertificateDataModel model) {
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
		model = (CertificateDataModel) session.getAttribute("model");
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

	public Certificate getCertificate() {
		return Certificate;
	}
	
	public void setCertificate(Certificate Certificate) {
		this.Certificate = Certificate;
	}

	public String getActNumber() {
		return actNumber;
	}

	public void setActNumber(String actNumber) {
		this.actNumber = actNumber;
	}
}
