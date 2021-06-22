package org.infosystema.iselect.dto.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@JsonInclude(Include.NON_NULL)
public class OnoiSignResponse {
	
	private String transactionId;
	private Boolean needCheckPhoto;
	
	public OnoiSignResponse() {}

	public Boolean getNeedCheckPhoto() {
		return needCheckPhoto;
	}
	
	public void setNeedCheckPhoto(Boolean needCheckPhoto) {
		this.needCheckPhoto = needCheckPhoto;
	}
	
	public String getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
}
