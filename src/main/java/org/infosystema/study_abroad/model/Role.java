package org.infosystema.study_abroad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "role")
public class Role extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;
	
    private String name;
    private String humanName;
	
    @Size(max = 255)
    @Column(name = "name", unique = true)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "human_name")
	public String getHumanName() {
		return humanName;
	}

	public void setHumanName(String humanName) {
		this.humanName = humanName;
	}
}
