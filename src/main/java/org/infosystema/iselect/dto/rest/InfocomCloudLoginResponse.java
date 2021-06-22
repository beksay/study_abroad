package org.infosystema.iselect.dto.rest;

public class InfocomCloudLoginResponse {
	
	private String createAt;
	private String authType;
	private Boolean isActive;
	private String inactiveReason;
	private Integer isPaid;
	private Integer errorCode;
	private String errorMessage;
	
	public InfocomCloudLoginResponse() {}

	public String getCreateAt() {
		return createAt;
	}
	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}
	
	public String getAuthType() {
		return authType;
	}	
	public void setAuthType(String authType) {
		this.authType = authType;
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
	
	public String getInactiveReason() {
		return inactiveReason;
	}	
	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}	
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	
	public Integer getIsPaid() {
		return isPaid;
	}	
	public void setIsPaid(Integer isPaid) {
		this.isPaid = isPaid;
	}
}
