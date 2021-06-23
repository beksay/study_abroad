package org.infosystema.study_abroad.controller.dict;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.infosystema.study_abroad.annotation.Logged;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.model.Countries;
import org.primefaces.event.SelectEvent;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Logged
@Named
@ConversationScoped
public class CountriesController extends Conversational {

	private static final long serialVersionUID = 1L;

	private Countries countries;

	@PostConstruct
	public void init() {
		countries = new Countries();
	}
	
	public void onRowSelect(SelectEvent event) throws IOException {
		countries = (Countries) event.getObject();
		FacesContext.getCurrentInstance().getExternalContext().redirect("/study_abroad/view/dictionaries/universities_list.xhtml?cid=" + getId());
	}

	public Countries getCountries() {
		return countries;
	}

	public void setCountries(Countries countries) {
		this.countries = countries;
	}

}
