package org.infosystema.study_abroad.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="countries")
public class Countries extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String name;
	
	public Countries() {}
	
	public Countries(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
}