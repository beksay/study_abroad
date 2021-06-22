package org.infosystema.iselect.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.infosystema.iselect.enums.UserStatus;


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
	private String username;
	private UserStatus status;
	private Subdivision subdivision;
	private String codes;
	private String inn;
	private String fullname;
	private String email;
	private String position;
	
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

	@Column(name = "user_name")
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public UserStatus getStatus() {
		return status;
	}
	
	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@ManyToOne
    @JoinColumn(name = "subdivision_id")
	public Subdivision getSubdivision() {
		return subdivision;
	}

	public void setSubdivision(Subdivision subdivision) {
		this.subdivision = subdivision;
	}

	public String getCodes() {
		return codes;
	}
	
	public void setCodes(String codes) {
		this.codes = codes;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	
}