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

import org.infosystema.study_abroad.enums.VisaStatus;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.User;



/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="visa")
public class Visa extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private VisaStep module;
	private Date date;
	private VisaStatus visaStatus;
	private Date dateCreated;
	private User user;
	
	@ManyToOne
	@JoinColumn(name="module_id")
	public VisaStep getModule() {
		return module;
	}
	
	public void setModule(VisaStep module) {
		this.module = module;
	}
	
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
	@Column(name="date")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="visa_status")
	public VisaStatus getVisaStatus() {
		return visaStatus;
	}
	
	public void setVisaStatus(VisaStatus visaStatus) {
		this.visaStatus = visaStatus;
	}
	
}