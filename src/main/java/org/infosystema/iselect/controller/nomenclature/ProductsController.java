package org.infosystema.iselect.controller.nomenclature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.conversiation.ConversationProducts;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.nomenclature.Products;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.ProductsService;
import org.infosystema.iselect.util.web.Messages;


@Named
@ViewScoped
public class ProductsController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4615344707884448644L;
	@EJB
	private ProductsService service;
	@EJB
	private DictionaryService dictionaryService;
	@Inject
	private ConversationProducts conversation;	
	
	private Products products;
    
	@PostConstruct
	public void init() {
		products=conversation.getProducts();
		if (products==null)	products= new Products();
	}
	
	public String add() {
		products = new Products();
		conversation.setProducts(products);
		return form();
	}
	
	public String edit(Products products) {
		this.products = products;
		conversation.setProducts(products);
		return form();
	}
	
	public String save() {
		if(products == null){
			FacesContext.getCurrentInstance().addMessage("form", new FacesMessage( FacesMessage.SEVERITY_ERROR,  Messages.getMessage("invalidData"), null) );
			return null;
		}
	
		if(!FacesContext.getCurrentInstance().getMessageList().isEmpty()) return null;
		try {
			if(products.getId() == null) {
				service.persist(products); 
			}else {
				service.merge(products);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setProducts(null);
		
	    return cancel();  
		
	}
	
	public List<Dictionary> getMeasurementUnitList(String query) {
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("type.id", 1, InequalityConstants.EQUAL));
        examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
        Long count = dictionaryService.countByExample(examples);

		return dictionaryService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
    }
	
	public List<Dictionary> getTnvedEaesList(String query) {
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("type.id", 3, InequalityConstants.EQUAL));
        examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
        Long count = dictionaryService.countByExample(examples);

		return dictionaryService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
    }
	
	public List<Dictionary> getProductTypeList(String query) {
        List<FilterExample> examples=new ArrayList<>();
        examples.add(new FilterExample("type.id", 8, InequalityConstants.EQUAL));
        examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
        
        Long count = dictionaryService.countByExample(examples);

		return dictionaryService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
    }
	
	public String delete(Products c) {
		service.remove(c);
		return cancel();
	}
	
	public String cancel() {
		products = null;
		return list();
	}
	
	private String list() {
		return "/view/nomenclature/products_list.xhtml?faces-redirect=true";
	}
	
	private String form() {
		return "/view/nomenclature/products_form.xhtml";
	}

	public Products getProducts() {
		return products;
	}
	
	public void setProducts(Products products) {
		this.products = products;
	}


}
