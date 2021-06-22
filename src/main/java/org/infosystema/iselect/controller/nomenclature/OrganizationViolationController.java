package org.infosystema.iselect.controller.nomenclature;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.iselect.beans.FilterExample;
import org.infosystema.iselect.beans.InequalityConstants;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.conversiation.ConversationOrganization;
import org.infosystema.iselect.model.nomenclature.OrganizationViolation;
import org.infosystema.iselect.model.nomenclature.ViolationOfLaw;
import org.infosystema.iselect.service.OrganizationViolationService;
import org.infosystema.iselect.service.ViolationOfLawService;

@Named
@ConversationScoped
public class OrganizationViolationController extends Conversational {

	private static final long serialVersionUID = 5959661098638400326L;
	@EJB
	private OrganizationViolationService service;
	@EJB
	private ViolationOfLawService violationService;
	@Inject
	private ConversationOrganization conversation;

	private OrganizationViolation organizationViolation;

	@PostConstruct
	public void init() {
		if (organizationViolation == null)
			organizationViolation = new OrganizationViolation();
	}

	public String save() {
		organizationViolation.setDateCreated(new Date());
		if (organizationViolation.getId() == null) {
			organizationViolation.setOrganization(conversation.getOrganization());
			organizationViolation = service.persist(organizationViolation);
		} else {
			organizationViolation = service.merge(organizationViolation);
		}
		organizationViolation = new OrganizationViolation();
		return "organization_violation.xhtml";
	}

	public String editData(OrganizationViolation organizationViolation) {
		this.organizationViolation = service.findById(organizationViolation.getId(), false);
		return "organization_violation.xhtml";
	}

	public String delete(OrganizationViolation organization_violation) {
		service.remove(organization_violation);
		return "organization_violation.xhtml";
	}

	public String cancel() {
		return "organization_list.xhtml";
	}

	public List<OrganizationViolation> getList() {
		List<FilterExample> examples = new ArrayList<>();
		if (conversation.getOrganization() != null) {
			examples.add(new FilterExample("organization", conversation.getOrganization(), InequalityConstants.EQUAL));
		} else {
			examples.add(new FilterExample("id", InequalityConstants.IS_NULL_SINGLE));
		}
		return service.findByExample(0, 100, examples);
	}

	public List<ViolationOfLaw> getViolationOfLawList(String query) {

		List<FilterExample> examples = new ArrayList<>();

		Long count = violationService.countByExample(examples);
		return violationService.findByExample(0, Math.toIntExact(count), examples).stream()
				.filter(t -> t.getViolationName().toLowerCase().startsWith(query.toLowerCase()))
				.collect(Collectors.toList());
	}

	public OrganizationViolation getOrganizationViolation() {
		return organizationViolation;
	}

	public void setOrganizationViolation(OrganizationViolation organizationViolation) {
		this.organizationViolation = organizationViolation;
	}

	public ConversationOrganization getConversation() {
		return conversation;
	}

	public void setConversation(ConversationOrganization conversation) {
		this.conversation = conversation;
	}
}
