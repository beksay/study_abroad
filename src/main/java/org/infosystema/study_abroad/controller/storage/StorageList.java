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
import org.infosystema.study_abroad.data.StorageModel;
import org.infosystema.study_abroad.model.app.Storage;
import org.infosystema.study_abroad.service.StorageService;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class StorageList implements Serializable {

	private static final long serialVersionUID = 8475958315897562353L;
	private StorageModel model;
	private Storage storage;
	private Integer first;

	@EJB
	private StorageService service;

	@PostConstruct
	private void init() {
		restoreState();
		filterData();
	}

	public void filterData() {

		List<FilterExample> filters = new ArrayList<>();

		model = new StorageModel(filters, service);

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
		model = (StorageModel) session.getAttribute("model");
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

	public StorageModel getModel() {
		return model;
	}

	public void setModel(StorageModel model) {
		this.model = model;
	}

	public Storage getStorage() {
		return storage;
	}

	public void setStorage(Storage storage) {
		this.storage = storage;
	}

}
