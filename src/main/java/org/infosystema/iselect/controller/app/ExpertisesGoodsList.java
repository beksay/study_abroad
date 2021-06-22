package org.infosystema.iselect.controller.app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.data.ExpertisesGoodsDataModel;
import org.infosystema.iselect.model.app.ExpertisesGoods;
import org.infosystema.iselect.service.ExpertisesGoodsService;
import org.infosystema.iselect.util.web.LoginUtil;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.event.data.PageEvent;

@Named
@ViewScoped
public class ExpertisesGoodsList implements Serializable {
	
	private static final long serialVersionUID = 8475958315897562353L;
	@EJB
	private ExpertisesGoodsService service;
	private ExpertisesGoodsDataModel model; 
	private Integer first;
	private ExpertisesGoods ExpertisesGoods;
	private String searchText;
	@Inject
	private LoginUtil loginUtil;

	public ExpertisesGoodsList() {}
	
	@PostConstruct
	private void init(){
		restoreState();
		filterData();
	}
	
	public void filterData(){	
		List<FilterExample> filters = new ArrayList<>();
		filters.add(new FilterExample("expertises.laboratory", loginUtil.getCurrentUser().getSubdivision(), InequalityConstants.EQUAL));
		if (searchText != null && searchText.length()>0) filters.add(new FilterExample("goods.products.name", '%' + searchText + '%', InequalityConstants.LIKE));
		model = new ExpertisesGoodsDataModel(filters, service);	
	}
	
	public void clearData() {
		init();
	}
	
	public ExpertisesGoodsDataModel getModel() {
		return model;
	}
	
	public void setModel(ExpertisesGoodsDataModel model) {
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
		model = (ExpertisesGoodsDataModel) session.getAttribute("model");
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

	public ExpertisesGoods getExpertisesGoods() {
		return ExpertisesGoods;
	}
	
	public void setExpertisesGoods(ExpertisesGoods ExpertisesGoods) {
		this.ExpertisesGoods = ExpertisesGoods;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
}
