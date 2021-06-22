package org.infosystema.study_abroad.dto.rest;

public class InfocomCloudSignRequest {
	
	private String hash;
	private String userToken;
	
	public InfocomCloudSignRequest() {}

	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}
	
	public String getUserToken() {
		return userToken;
	}
	
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

}
