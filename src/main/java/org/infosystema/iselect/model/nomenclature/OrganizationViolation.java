package org.infosystema.iselect.model.nomenclature;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.infosystema.iselect.model.AbstractEntity;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="organization_violation")
public class OrganizationViolation extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private Organization organization;
	private ViolationOfLaw violation;
	private Date validity;
	private Date dateCreated;

	@ManyToOne
	@JoinColumn(name="organization")
	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	@ManyToOne
	@JoinColumn(name="violation")
	public ViolationOfLaw getViolation() {
		return violation;
	}

	public void setViolation(ViolationOfLaw violation) {
		this.violation = violation;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
}