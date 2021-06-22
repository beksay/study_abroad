package org.infosystema.iselect.dto.rest;

public class InfocomCloudCertResponse {
	
	private String createAt;
	private String expireAt;
	private Integer subjectType;
	private String personIdnp;
	private String personFio;
	private String organizationInn;
	private String organizationName;
	private Boolean isActive;
	private String token;
	
	private Integer errorCode;
	private String errorMessage;
	
	public InfocomCloudCertResponse() {}

	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}

	public String getExpireAt() {
		return expireAt;
	}
	public void setExpireAt(String expireAt) {
		this.expireAt = expireAt;
	}

	public Integer getSubjectType() {
		return subjectType;
	}
	public void setSubjectType(Integer subjectType) {
		this.subjectType = subjectType;
	}

	public String getPersonIdnp() {
		return personIdnp;
	}
	public void setPersonIdnp(String personIdnp) {
		this.personIdnp = personIdnp;
	}

	public String getPersonFio() {
		return personFio;
	}
	public void setPersonFio(String personFio) {
		this.personFio = personFio;
	}

	public String getOrganizationInn() {
		return organizationInn;
	}
	public void setOrganizationInn(String organizationInn) {
		this.organizationInn = organizationInn;
	}

	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}	
}
