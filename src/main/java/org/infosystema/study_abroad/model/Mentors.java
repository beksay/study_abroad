package org.infosystema.study_abroad.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="mentors")
public class Mentors extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;
	private String pin;
	private Date dateCreated;
	private String firstname;
	private String lastname;
	private String patronymic;
	private Countries country;
	private String city;
	private String address;
	private String phone;
	private Date birthDate;
	private Attachment profile;
	private User company;
	
	public String getPin() {
		return pin;
	}
	
	public void setPin(String pin) {
		this.pin = pin;
	}
	
	@Temporal(TemporalType.DATE)
	@Column(name="birth_date")
	public Date getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	@ManyToOne
	@JoinColumn (name="profile_id")
	public Attachment getProfile() {
		return profile;
	}
	
	public void setProfile(Attachment profile) {
		this.profile = profile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@ManyToOne
	@JoinColumn (name="company_id")
	public User getCompany() {
		return company;
	}
	
	public void setCompany(User company) {
		this.company = company;
	}
	
	@Transient
	public String getFullname() {
		StringBuffer buffer = new StringBuffer();
		
		if(lastname != null) buffer.append(lastname + " ");
		if(firstname != null) buffer.append(firstname + " ");
		if(patronymic != null) buffer.append(patronymic + " ");
		
		return buffer.toString();
	}

	@ManyToOne
	@JoinColumn (name="country_id")
	public Countries getCountry() {
		return country;
	}
	
	public void setCountry(Countries country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}