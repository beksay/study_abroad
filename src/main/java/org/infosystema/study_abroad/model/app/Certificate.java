package org.infosystema.study_abroad.model.app;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.study_abroad.enums.CertificateStatus;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.model.nomenclature.ViolationOfLaw;


@Entity
@Access(AccessType.PROPERTY)
@Table(name="certificate")
public class Certificate extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private Date date;
	private String blankNumber;
	private String docNumber;
	private String additionalDeclaration;
	private Inspection inspection;
	private CertificateStatus status;
	private String documentBy;
	private Dictionary controlMeasures;
	private Dictionary actActivities;
	private ViolationOfLaw violationOfLaw;
	private Date validity;
	private String givenBased;
	private User inspector;
	private String notes;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name="inspection")
	public Inspection getInspection() {
		return inspection;
	}
	
	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	@Column(name = "blank_number")
	public String getBlankNumber() {
		return blankNumber;
	}

	public void setBlankNumber(String blankNumber) {
		this.blankNumber = blankNumber;
	}

	@Column(name = "doc_number")
	public String getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(String docNumber) {
		this.docNumber = docNumber;
	}

	@Column(name = "additional_declaration",length = 2000)
	public String getAdditionalDeclaration() {
		return additionalDeclaration;
	}

	public void setAdditionalDeclaration(String additionalDeclaration) {
		this.additionalDeclaration = additionalDeclaration;
	}

	@Enumerated(EnumType.ORDINAL)
	public CertificateStatus getStatus() {
		return status;
	}

	public void setStatus(CertificateStatus status) {
		this.status = status;
	}

	@Column(name = "document_by")
	public String getDocumentBy() {
		return documentBy;
	}

	public void setDocumentBy(String documentBy) {
		this.documentBy = documentBy;
	}

	@ManyToOne
	@JoinColumn(name="control_measures")
	public Dictionary getControlMeasures() {
		return controlMeasures;
	}

	public void setControlMeasures(Dictionary controlMeasures) {
		this.controlMeasures = controlMeasures;
	}

	@ManyToOne
	@JoinColumn(name="act_activities")
	public Dictionary getActActivities() {
		return actActivities;
	}

	public void setActActivities(Dictionary actActivities) {
		this.actActivities = actActivities;
	}

	@ManyToOne
	@JoinColumn(name="violation_of_law")
	public ViolationOfLaw getViolationOfLaw() {
		return violationOfLaw;
	}

	public void setViolationOfLaw(ViolationOfLaw violationOfLaw) {
		this.violationOfLaw = violationOfLaw;
	}

	public Date getValidity() {
		return validity;
	}

	public void setValidity(Date validity) {
		this.validity = validity;
	}

	@Column(name = "given_based")
	public String getGivenBased() {
		return givenBased;
	}

	public void setGivenBased(String givenBased) {
		this.givenBased = givenBased;
	}

	@ManyToOne
	@JoinColumn(name="inspector")
	public User getInspector() {
		return inspector;
	}

	public void setInspector(User inspector) {
		this.inspector = inspector;
	}

	@Column(length = 2000)
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

}