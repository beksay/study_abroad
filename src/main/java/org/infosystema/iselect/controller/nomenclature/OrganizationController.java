package org.infosystema.iselect.controller.nomenclature;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.infosystema.iselect.conversiation.ConversationOrganization;
import org.infosystema.iselect.enums.MemberType;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.nomenclature.Organization;
import org.infosystema.iselect.service.DictionaryService;
import org.infosystema.iselect.service.OrganizationService;
import org.infosystema.iselect.util.web.Messages;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class OrganizationController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4986485619586079806L;
	@EJB
	private OrganizationService service;
	@EJB
	private DictionaryService dictionaryService;
	@Inject
	private ConversationOrganization conversation;

	private Organization organization;

	@PostConstruct
	public void init() {
		organization = conversation.getOrganization();
		if (organization == null)
			organization = new Organization();
	}

	public String add() {
		organization = new Organization();
		conversation.setOrganization(organization);
		return form();
	}

	public String edit(Organization organization) {
		this.organization = organization;
		conversation.setOrganization(organization);
		return form();
	}

	public String save() {
		if (organization == null) {
			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}

		if (!FacesContext.getCurrentInstance().getMessageList().isEmpty())
			return null;
		try {
			if (organization.getId() == null) {
				service.persist(organization);
			} else {
				service.merge(organization);
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					Messages.getMessage("saveError"), Messages.getMessage("saveError")));
			e.printStackTrace();
			return null;
		}
		conversation.setOrganization(null);

		return cancel();

	}

	public void onRowSelect(SelectEvent event) throws IOException {
		organization = (Organization) event.getObject();

		conversation.setOrganization(organization);

		FacesContext.getCurrentInstance().getExternalContext()
				.redirect("/iselect/view/nomenclature/organization_violation.xhtml?cid=" + conversation.getId());
	}
	
	public List<Dictionary> getLegalFormList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 5, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictionaryService.countByExample(examples);

		return dictionaryService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<Dictionary> getWorldClassifierList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id", 2, InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictionaryService.countByExample(examples);

		return dictionaryService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<Dictionary> getAddressList(String query) {
		List<FilterExample> examples = new ArrayList<>();
		examples.add(new FilterExample("type.id",4 , InequalityConstants.EQUAL));
		examples.add(new FilterExample("active", true, InequalityConstants.EQUAL));
		Long count = dictionaryService.countByExample(examples);

		return dictionaryService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getName().toLowerCase().startsWith(query.toLowerCase())).collect(Collectors.toList());
	}

	public List<MemberType> getAllMemberType() {
		return Arrays.asList(MemberType.values());
	}

	public String delete(Organization c) {
		service.remove(c);
		return cancel();
	}

	public String cancel() {
		organization = null;
		return list();
	}

	private String list() {
		return "/view/nomenclature/organization_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/nomenclature/organization_form.xhtml";
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

}
