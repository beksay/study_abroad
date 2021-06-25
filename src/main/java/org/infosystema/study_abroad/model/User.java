package org.infosystema.study_abroad.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.infosystema.study_abroad.enums.UserStatus;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String password;
	private Role role;
	private String email;
	private UserStatus status;
	private Person person;
	private String companyName;
	private String phone;
	private User company;
	
	public User() {
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToOne
    @JoinColumn(name = "role_id")
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	@ManyToOne
    @JoinColumn(name="person_id")
	public Person getPerson() {
		return person;
	}
	
	public void setPerson(Person person) {
		this.person = person;
	}
	
	@ManyToOne
    @JoinColumn(name="company_id")
	public User getCompany() {
		return company;
	}
	
	public void setCompany(User company) {
		this.company = company;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public UserStatus getStatus() {
		return status;
	}
	
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}