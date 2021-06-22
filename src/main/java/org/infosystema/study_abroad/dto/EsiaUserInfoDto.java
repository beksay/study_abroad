package org.infosystema.study_abroad.dto;

import java.util.Date;

public class EsiaUserInfoDto {

	private Boolean organization=false;
	private String sub;
	private String organizationInn;
	private String positionName;
	private String personInn;
	private String citizenship;
	private String familyName;
	private String givenName;
	private String middleName;
	private String name;
	private String gender;
	private Date birthdate;
	private String email;
	private Boolean emailVerified;
	private String phoneNomber;
	private Boolean phoneNomberVerified;
	private String authType;
	private String accessToken;
	
	
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}
	public String getOrganizationInn() {
		return organizationInn;
	}
	public void setOrganizationInn(String organizationInn) {
		this.organizationInn = organizationInn;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPersonInn() {
		return personInn;
	}
	public void setPersonInn(String personInn) {
		this.personInn = personInn;
	}
	public String getCitizenship() {
		return citizenship;
	}
	public void setCitizenship(String citizenship) {
		this.citizenship = citizenship;
	}
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Boolean getEmailVerified() {
		return emailVerified;
	}
	public void setEmailVerified(Boolean emailVerified) {
		this.emailVerified = emailVerified;
	}
	public String getPhoneNomber() {
		return phoneNomber;
	}
	public void setPhoneNomber(String phoneNomber) {
		this.phoneNomber = phoneNomber;
	}
	public Boolean getPhoneNomberVerified() {
		return phoneNomberVerified;
	}
	public void setPhoneNomberVerified(Boolean phoneNomberVerified) {
		this.phoneNomberVerified = phoneNomberVerified;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getOrganization() {
		return organization;
	}
	public void setOrganization(Boolean organization) {
		this.organization = organization;
	}
	public String getAuthType() {
		return authType;
	}
	public void setAuthType(String authType) {
		this.authType = authType;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	
	
	
}
