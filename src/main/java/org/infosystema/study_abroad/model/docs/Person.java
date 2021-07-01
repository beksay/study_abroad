package org.infosystema.study_abroad.model.docs;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Attachment;
import org.infosystema.study_abroad.model.Countries;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.Mentors;
import org.infosystema.study_abroad.model.User;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="person")
public class Person extends AbstractEntity<Integer> {
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
	private Mentors mentor;
	private Dictionary englishLevel;
	private Date birthDate;
	private Attachment profile;
	private User company;
	private String toefl;
	private String ielts;
	private User personUser;

	private Set<Dictionary> languages;
	
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
	@JoinColumn (name="mentor_id")
	public Mentors getMentor() {
		return mentor;
	}
	
	public void setMentor(Mentors mentor) {
		this.mentor = mentor;
	}
	
	@ManyToOne
	@JoinColumn(name="person_user_id")
	public User getPersonUser() {
		return personUser;
	}
	
	public void setPersonUser(User personUser) {
		this.personUser = personUser;
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

	@ManyToOne
	@JoinColumn (name="english_level_id")
	public Dictionary getEnglishLevel() {
		return englishLevel;
	}
	
	public void setEnglishLevel(Dictionary englishLevel) {
		this.englishLevel = englishLevel;
	}

	public String getToefl() {
		return toefl;
	}

	public void setToefl(String toefl) {
		this.toefl = toefl;
	}

	public String getIelts() {
		return ielts;
	}

	public void setIelts(String ielts) {
		this.ielts = ielts;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "person_languages", 
	  joinColumns = @JoinColumn(name = "person_id"), 
	  inverseJoinColumns = @JoinColumn(name = "dictionary_id"))	
	public Set<Dictionary> getLanguages() {
		return languages;
	}

	public void setLanguages(Set<Dictionary> languages) {
		this.languages = languages;
	}
	
}