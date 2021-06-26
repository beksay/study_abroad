package org.infosystema.study_abroad.model.docs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.study_abroad.enums.DocStatus;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Countries;
import org.infosystema.study_abroad.model.Universities;
import org.infosystema.study_abroad.model.UniversityType;
import org.infosystema.study_abroad.model.User;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="applications")
public class Applications extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private Countries countries;
	private UniversityType universityType;
	private Universities universities;
	private Date dateCreated;
	private Date dateModify;
	private DocStatus status;
	private User user;
	
	@ManyToOne
	@JoinColumn (name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_modify")
	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public DocStatus getStatus() {
		return status;
	}
	
	public void setStatus(DocStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="country_id")
	public Countries getCountries() {
		return countries;
	}

	public void setCountries(Countries countries) {
		this.countries = countries;
	}

	@ManyToOne
	@JoinColumn(name="university_id")
	public Universities getUniversities() {
		return universities;
	}

	public void setUniversities(Universities universities) {
		this.universities = universities;
	}
	
	@ManyToOne
	@JoinColumn(name="university_type_id")
	public UniversityType getUniversityType() {
		return universityType;
	}
	
	public void setUniversityType(UniversityType universityType) {
		this.universityType = universityType;
	}
	
}