package org.infosystema.iselect.dto.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class OnoiOrganizationInfo {
	
	private String organizationInn;
	private String organizationName;
	
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

}
