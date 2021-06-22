package org.infosystema.iselect.model.nomenclature;

import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.infosystema.iselect.model.AbstractEntity;
import org.infosystema.iselect.model.Attachment;
import org.infosystema.iselect.model.Dictionary;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="quarantine_object")
public class QuarantineObject extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String name;
	private String enName;
	private String kgName;
	private Dictionary quarantineType;
	private Set<Attachment> documents;
	
	@Field(store = Store.YES, analyze = Analyze.NO)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="en_name")
	public String getEnName() {
		return enName;
	}
	
	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	@Column(name="kg_name")
	public String getKgName() {
		return kgName;
	}
	
	public void setKgName(String kgName) {
		this.kgName = kgName;
	}

	@ManyToOne
	@JoinColumn(name="quarantine_type")
	public Dictionary getQuarantineType() {
		return quarantineType;
	}
	
	public void setQuarantineType(Dictionary quarantineType) {
		this.quarantineType = quarantineType;
	}
	
	@ManyToMany(cascade={CascadeType.REMOVE}, fetch=FetchType.LAZY)
	@JoinTable(name="quarantine_object_attachment", 
		joinColumns=@JoinColumn(name="quarantine_object_id"),
		inverseJoinColumns=@JoinColumn(name="attachment_id")
	)
	public Set<Attachment> getDocuments() {
		return documents;
	}
	
	public void setDocuments(Set<Attachment> documents) {
		this.documents = documents;
	}
	
}