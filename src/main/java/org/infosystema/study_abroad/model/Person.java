package org.infosystema.study_abroad.model;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="person")
public class Person extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String inn;
	private String fullname;
	private String phone;
	
	public Person() {
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}