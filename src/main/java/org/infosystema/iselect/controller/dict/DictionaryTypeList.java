package org.infosystema.iselect.controller.dict;

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
import org.infosystema.iselect.data.DictionaryTypeDataModel;
import org.infosystema.iselect.model.DictionaryType;
import org.infosystema.iselect.service.DictionaryTypeService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class DictionaryTypeList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	private DictionaryTypeDataModel model; 
	private DictionaryType dictionaryType;
	private Integer first;
	private String searchString;
	
	@EJB
	private DictionaryTypeService service;

	public DictionaryTypeList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){
		
		List<FilterExample> filters = new ArrayList<>();
		if (searchString != null && searchString.length()>0) {
			filters.add(new FilterExample(true, "name", '%' + searchString + '%', InequalityConstants.LIKE, true));
			filters.add(new FilterExample(true, "shortName", '%' + searchString + '%', InequalityConstants.LIKE, true));
		}
		model = new DictionaryTypeDataModel(filters, service);
		
	}
	
	
	public DictionaryTypeDataModel getModel() {
		return model;
	}
	
	public void setModel(DictionaryTypeDataModel model) {
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
		model = (DictionaryTypeDataModel) session.getAttribute("model");
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

	public DictionaryType getDictionaryType() {
		return dictionaryType;
	}

	public void setDictionaryType(DictionaryType dictionaryType) {
		this.dictionaryType = dictionaryType;
	} 
}
