package org.infosystema.study_abroad.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="subdivision")
public class Subdivision extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String code;
	private String name;
	private Subdivision parent;
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne
	@JoinColumn (name="parent_id")
	public Subdivision getParent() {
		return parent;
	}
	
	public void setParent(Subdivision parent) {
		this.parent = parent;
	}
	
}