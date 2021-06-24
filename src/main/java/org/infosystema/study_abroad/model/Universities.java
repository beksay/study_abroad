package org.infosystema.study_abroad.model;

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


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="universities")
public class Universities extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private Countries countries;
	private UniversityType universityType;
	private String name;
	private String shortName;
	private String city;
	private Integer foundYear;
	private String description;
	private Set<Attachment> attachments;
	
	public Universities() {}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="short_name")
	public String getShortName() {
		return shortName;
	}
	
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Column(name="found_year")
	public Integer getFoundYear() {
		return foundYear;
	}

	public void setFoundYear(Integer foundYear) {
		this.foundYear = foundYear;
	}

	@Column(length = 10000)
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
    @JoinColumn(name = "countries_id")
	public Countries getCountries() {
		return countries;
	}

	public void setCountries(Countries countries) {
		this.countries = countries;
	}

	@ManyToOne
    @JoinColumn(name = "university_type_id")
	public UniversityType getUniversityType() {
		return universityType;
	}

	public void setUniversityType(UniversityType universityType) {
		this.universityType = universityType;
	}
	
	@ManyToMany(cascade={CascadeType.REMOVE}, fetch=FetchType.LAZY)
	@JoinTable(name="university_attachment", 
		joinColumns=@JoinColumn(name="university_id"),
		inverseJoinColumns=@JoinColumn(name="attachment_id")
	)
	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
	
}