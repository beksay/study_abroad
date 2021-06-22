package org.infosystema.iselect.dto.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@JsonInclude(Include.NON_NULL)
public class OnoiSignRequest {
	
	private String hash;
	private String comment;
	
	public OnoiSignRequest() {}

	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}
}
