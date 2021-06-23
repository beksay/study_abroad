package org.infosystema.study_abroad.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="dict")
public class Dictionary extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private String enName;
	private Boolean active;
	private DictionaryType type;
	private Dictionary parent;
	
	@Transient
	@Override
	@Field(name = "_id", store = Store.YES, analyze = Analyze.NO)
	public Integer getId() {
		return super.getId();
	}
	
	@Field(store = Store.YES, analyze = Analyze.NO)
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@ManyToOne
	@JoinColumn (name="type")
	public DictionaryType getType() {
		return type;
	}
	
	public void setType(DictionaryType type) {
		this.type = type;
	}

	@ManyToOne
	@JoinColumn (name="parent_id")
	public Dictionary getParent() {
		return parent;
	}
	
	public void setParent(Dictionary parent) {
		this.parent = parent;
	}
	
}