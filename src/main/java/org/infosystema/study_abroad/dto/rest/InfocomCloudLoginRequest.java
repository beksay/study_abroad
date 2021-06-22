package org.infosystema.study_abroad.dto.rest;

public class InfocomCloudLoginRequest {
	
	private String personIdnp;
	private String organizationInn;
	private String byPin;
	
	public InfocomCloudLoginRequest() {}

	public String getPersonIdnp() {
		return personIdnp;
	}

	public void setPersonIdnp(String personIdnp) {
		this.personIdnp = personIdnp;
	}

	public String getOrganizationInn() {
		return organizationInn;
	}

	public void setOrganizationInn(String organizationInn) {
		this.organizationInn = organizationInn;
	}
	
	public String getByPin() {
		return byPin;
	}
	
	public void setByPin(String byPin) {
		this.byPin = byPin;
	}

}
