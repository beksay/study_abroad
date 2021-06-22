package org.infosystema.iselect.dto.rest;

public class InfocomCloudSignResponse {
	
	private Boolean isSuccesfull;
	private String sign;
	private Long timestamp;
	
	public InfocomCloudSignResponse() {}

	public Boolean getIsSuccesfull() {
		return isSuccesfull;
	}

	public void setIsSuccesfull(Boolean isSuccesfull) {
		this.isSuccesfull = isSuccesfull;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
