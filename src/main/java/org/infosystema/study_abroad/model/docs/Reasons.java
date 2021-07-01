package org.infosystema.study_abroad.model.docs;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.study_abroad.model.User;

@Entity
@Table(name="reasons")
public class Reasons extends Module {

	private static final long serialVersionUID = 1L;

	private String reasons;
	private Date dateCreated;
	private User user;
	
	@ManyToOne
	@JoinColumn (name="user_id")
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}

	@Column(length = 2000)
	public String getReasons() {
		return reasons;
	}
	
	public void setReasons(String reasons) {
		this.reasons = reasons;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
