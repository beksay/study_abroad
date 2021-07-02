package org.infosystema.study_abroad.model.docs;

import java.util.Date;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.study_abroad.enums.DocStatus;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Attachment;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.User;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="documents")
public class Documents extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private DocumentsStep module;
	private VisaDocument visaModule;
	private Dictionary document;
	private Date date;
	private DocStatus status;
	private Set<Attachment> attachments;
	private User user;
	
	@ManyToOne
	@JoinColumn(name="module_id")
	public DocumentsStep getModule() {
		return module;
	}
	
	public void setModule(DocumentsStep module) {
		this.module = module;
	}
	
	@ManyToOne
	@JoinColumn(name="visa_module_id")
	public VisaDocument getVisaModule() {
		return visaModule;
	}
	
	public void setVisaModule(VisaDocument visaModule) {
		this.visaModule = visaModule;
	}
	
	@ManyToOne
	@JoinColumn (name="document_id")
	public Dictionary getDocument() {
		return document;
	}

	public void setDocument(Dictionary document) {
		this.document = document;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Enumerated(EnumType.ORDINAL)
	public DocStatus getStatus() {
		return status;
	}

	public void setStatus(DocStatus status) {
		this.status = status;
	}

	@ManyToMany(cascade={CascadeType.REMOVE}, fetch=FetchType.LAZY)
	@JoinTable(name="document_attachment", 
		joinColumns=@JoinColumn(name="document_id"),
		inverseJoinColumns=@JoinColumn(name="attachment_id")
	)
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}