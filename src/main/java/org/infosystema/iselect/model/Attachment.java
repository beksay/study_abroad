package org.infosystema.iselect.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="attachment")
public class Attachment extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String fileName;
	private String fileType;
	private String repositoryLink;
	private Date dateCreated;
	private String repositoryName;
	
	@Column(name="file_name")
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Column(name="filte_type")
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	@Column(name="repository_link")
	public String getRepositoryLink() {
		return repositoryLink;
	}

	public void setRepositoryLink(String repositoryLink) {
		this.repositoryLink = repositoryLink;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@Column(name = "new_repository_name")
    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }
}