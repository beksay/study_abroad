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

import org.infosystema.study_abroad.enums.AppStatus;
import org.infosystema.study_abroad.enums.Applicant;
import org.infosystema.study_abroad.enums.ApplicationType;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.Subdivision;
import org.infosystema.study_abroad.model.User;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="applications")
public class Applications extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String number;
	private Date dateCreated;
	private ApplicationType type;
	private Applicant applicant;
	private Dictionary legalForm;
	private String inn;
	private String okpo;
	private String companyName;
	private String directorName;
	private Dictionary country;
	private String currentAddress;
	private String contact;
	private String email;
	private AppStatus status;
	private User user;
	private String note;
	private Subdivision subdivision;
	private Integer year;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Enumerated(EnumType.ORDINAL)
	public ApplicationType getType() {
		return type;
	}
	
	public void setType(ApplicationType type) {
		this.type = type;
	}

	@Enumerated(EnumType.ORDINAL)
	public Applicant getApplicant() {
		return applicant;
	}

	public void setApplicant(Applicant applicant) {
		this.applicant = applicant;
	}

	@ManyToOne
	@JoinColumn(name="legal_form")
	public Dictionary getLegalForm() {
		return legalForm;
	}

	public void setLegalForm(Dictionary legalForm) {
		this.legalForm = legalForm;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getOkpo() {
		return okpo;
	}

	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	@Column(name="company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name="director_name")
	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	@Column(name="current_address")
	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Enumerated(EnumType.ORDINAL)
	public AppStatus getStatus() {
		return status;
	}

	public void setStatus(AppStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@ManyToOne
	@JoinColumn(name="subdivision")
	public Subdivision getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(Subdivision subdivision) {
		this.subdivision = subdivision;
	}

	@ManyToOne
	@JoinColumn(name="country_id")
	public Dictionary getCountry() {
		return country;
	}

	public void setCountry(Dictionary country) {
		this.country = country;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}
	
}