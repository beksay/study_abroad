package org.infosystema.study_abroad.model.docs;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.study_abroad.model.Attachment;
import org.infosystema.study_abroad.model.User;

@Entity
@Table(name="visa_preparation")
public class VisaPreparation extends Module {

	private static final long serialVersionUID = 1L;

	private Set<Attachment> attachments;
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

	@ManyToMany(cascade={CascadeType.REMOVE}, fetch=FetchType.LAZY)
	@JoinTable(name="visa_preparation_attachment", 
		joinColumns=@JoinColumn(name="visa_preparation_id"),
		inverseJoinColumns=@JoinColumn(name="attachment_id")
	)
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
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
