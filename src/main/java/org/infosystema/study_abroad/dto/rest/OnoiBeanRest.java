package org.infosystema.study_abroad.dto.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@JsonInclude(Include.NON_NULL)
public class OnoiBeanRest {
	
	private Boolean isConfirmed;
	private String sessionId;
	private String inn;
	private String innOrganisation;
	private String fullName;
	private String organisationName;
	private String roles;
	private Integer userId;
	private Boolean needCheckPhoto;
	private OnoiOrganizationInfo organizationInfo;
	
	public OnoiBeanRest() {}

	public Boolean getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(Boolean isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getInnOrganisation() {
		return innOrganisation;
	}

	public void setInnOrganisation(String innOrganisation) {
		this.innOrganisation = innOrganisation;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	
	public String getRoles() {
		return roles;
	}
	public void setRoles(String roles) {
		this.roles = roles;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getNeedCheckPhoto() {
		return needCheckPhoto;
	}

	public void setNeedCheckPhoto(Boolean needCheckPhoto) {
		this.needCheckPhoto = needCheckPhoto;
	}

	public OnoiOrganizationInfo getOrganizationInfo() {
		return organizationInfo;
	}

	public void setOrganizationInfo(OnoiOrganizationInfo organizationInfo) {
		this.organizationInfo = organizationInfo;
	}
}
