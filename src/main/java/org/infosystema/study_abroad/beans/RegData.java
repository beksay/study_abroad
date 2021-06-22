package org.infosystema.study_abroad.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class RegData implements Serializable {

	private static final long serialVersionUID = -7025909035243637102L;
	private String inn;
	private String firstName;
	private String lastName;
	private String middleName;
	private String firstNameLat;
	private String lastNameLat;
	private String middleNameLat;
	private Date dateBirth;
	private String localPassportNumber;
	private String localPassportSeries;
	private Date localPassportIssuedDate;
	private Date localPassportExpiry;
	private String internationalPassportNumber;
	private String internationalPassportSeries;
	private Date internationalPassportExpiry;
	private Date internationalPassportIssuedDate;
	private String addressCode;
	private String address;
	private String national;
	private String gender;
	protected Date issuedDateANInvalid;
    protected String addressANInvalid;

	public RegData() {}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public String getFirstNameLat() {
		return firstNameLat;
	}
	
	public void setFirstNameLat(String firstNameLat) {
		this.firstNameLat = firstNameLat;
	}
	
	public String getLastNameLat() {
		return lastNameLat;
	}
	
	public void setLastNameLat(String lastNameLat) {
		this.lastNameLat = lastNameLat;
	}
	
	public String getMiddleNameLat() {
		return middleNameLat;
	}
	
	public void setMiddleNameLat(String middleNameLat) {
		this.middleNameLat = middleNameLat;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}

	public String getLocalPassportNumber() {
		return localPassportNumber;
	}

	public void setLocalPassportNumber(String localPassportNumber) {
		this.localPassportNumber = localPassportNumber;
	}

	public String getLocalPassportSeries() {
		return localPassportSeries;
	}

	public void setLocalPassportSeries(String localPassportSeries) {
		this.localPassportSeries = localPassportSeries;
	}

	public Date getLocalPassportExpiry() {
		return localPassportExpiry;
	}

	public void setLocalPassportExpiry(Date localPassportExpiry) {
		this.localPassportExpiry = localPassportExpiry;
	}

	public String getInternationalPassportNumber() {
		return internationalPassportNumber;
	}

	public void setInternationalPassportNumber(String internationalPassportNumber) {
		this.internationalPassportNumber = internationalPassportNumber;
	}

	public String getInternationalPassportSeries() {
		return internationalPassportSeries;
	}

	public void setInternationalPassportSeries(String internationalPassportSeries) {
		this.internationalPassportSeries = internationalPassportSeries;
	}

	public Date getInternationalPassportExpiry() {
		return internationalPassportExpiry;
	}

	public void setInternationalPassportExpiry(Date internationalPassportExpiry) {
		this.internationalPassportExpiry = internationalPassportExpiry;
	}

	public String getAddressCode() {
		return addressCode;
	}

	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}
	
	public Date getLocalPassportIssuedDate() {
		return localPassportIssuedDate;
	}
	
	public void setLocalPassportIssuedDate(Date localPassportIssuedDate) {
		this.localPassportIssuedDate = localPassportIssuedDate;
	}
	
	public Date getInternationalPassportIssuedDate() {
		return internationalPassportIssuedDate;
	}
	
	public void setInternationalPassportIssuedDate(Date internationalPassportIssuedDate) {
		this.internationalPassportIssuedDate = internationalPassportIssuedDate;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public Date getIssuedDateANInvalid() {
		return issuedDateANInvalid;
	}

	public void setIssuedDateANInvalid(Date issuedDateANInvalid) {
		this.issuedDateANInvalid = issuedDateANInvalid;
	}

	public String getAddressANInvalid() {
		return addressANInvalid;
	}

	public void setAddressANInvalid(String addressANInvalid) {
		this.addressANInvalid = addressANInvalid;
	}

	@Override
	public String toString() {
		return "RegData [inn=" + inn + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName="
				+ middleName + ", firstNameLat=" + firstNameLat + ", lastNameLat=" + lastNameLat + ", middleNameLat="
				+ middleNameLat + ", dateBirth=" + dateBirth + ", localPassportNumber=" + localPassportNumber
				+ ", localPassportSeries=" + localPassportSeries + ", localPassportIssuedDate="
				+ localPassportIssuedDate + ", localPassportExpiry=" + localPassportExpiry
				+ ", internationalPassportNumber=" + internationalPassportNumber + ", internationalPassportSeries="
				+ internationalPassportSeries + ", internationalPassportExpiry=" + internationalPassportExpiry
				+ ", internationalPassportIssuedDate=" + internationalPassportIssuedDate + ", addressCode="
				+ addressCode + ", address=" + address + ", national=" + national + ", gender=" + gender + "]";
	}

}
