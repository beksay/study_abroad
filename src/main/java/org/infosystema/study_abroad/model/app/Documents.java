package org.infosystema.study_abroad.model.app;

import java.util.Date;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Attachment;


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
	private String number;
	private String name;
	private String description;
	private Date issueDate;
	private Set<Attachment> attachments;
	private DocumentsStep module;
	
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
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
	@JoinColumn (name="module_id")
	public DocumentsStep getModule() {
		return module;
	}

	public void setModule(DocumentsStep module) {
		this.module = module;
	}

}