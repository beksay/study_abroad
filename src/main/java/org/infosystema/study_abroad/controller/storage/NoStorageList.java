package org.infosystema.study_abroad.controller.storage;

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
import org.infosystema.study_abroad.data.NoStorageModel;
import org.infosystema.study_abroad.model.app.NoStorage;
import org.infosystema.study_abroad.service.NoStorageService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class NoStorageList implements Serializable {

	private static final long serialVersionUID = 8475958315897562353L;
	private NoStorageModel model;
	private NoStorage noStorage;
	private Integer first;

	@EJB
	private NoStorageService service;

	@PostConstruct
	private void init() {
		restoreState();
		filterData();
	}

	public void filterData() {

		List<FilterExample> filters = new ArrayList<>();

		model = new NoStorageModel(filters, service);

	}

	public Integer getFirst() {
		return first;
	}

	public void setFirst(Integer first) {
		this.first = first;
	}

	public void saveState() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("model", model);
		session.setAttribute("first", first);
	}

	public void restoreState() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		model = (NoStorageModel) session.getAttribute("model");
		first = (Integer) session.getAttribute("first");
	}

	public void removeState() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("model", null);
		session.setAttribute("first", null);

		model = null;
		first = null;
	}

	public void onPageChange(PageEvent event) {
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		HttpSession session = request.getSession();
		setFirst(((DataTable) event.getSource()).getRows() * event.getPage());
		session.setAttribute("first", first);
	}

	public NoStorageModel getModel() {
		return model;
	}

	public void setModel(NoStorageModel model) {
		this.model = model;
	}

	public NoStorage getNoStorage() {
		return noStorage;
	}

	public void setNoStorage(NoStorage noStorage) {
		this.noStorage = noStorage;
	}

}
