package org.infosystema.iselect.dto.rest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@JsonInclude(Include.NON_NULL)
public class OnoiSignDocumentResult {
	
	private String transactionId;
	private String status;
	private String errorMessage;
	private String signDate;
	private String documentHash;
	private String documentSignaturesBase64;
	private String comment;
	private String customerInn;
	private String customerOrganizationInn;
	private String customerFullName;
	private String responsiblePersonOfCompanyInn;
	private String responsiblePersonOfCompanyOrganizationInn;
	private String responsiblePersonOfCompanyFullName;
	
	public OnoiSignDocumentResult() {}

	public String getTransactionId() {
		return transactionId;
	}
	
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getDocumentHash() {
		return documentHash;
	}

	public void setDocumentHash(String documentHash) {
		this.documentHash = documentHash;
	}

	public String getDocumentSignaturesBase64() {
		return documentSignaturesBase64;
	}

	public void setDocumentSignaturesBase64(String documentSignaturesBase64) {
		this.documentSignaturesBase64 = documentSignaturesBase64;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCustomerInn() {
		return customerInn;
	}

	public void setCustomerInn(String customerInn) {
		this.customerInn = customerInn;
	}

	public String getCustomerOrganizationInn() {
		return customerOrganizationInn;
	}

	public void setCustomerOrganizationInn(String customerOrganizationInn) {
		this.customerOrganizationInn = customerOrganizationInn;
	}

	public String getCustomerFullName() {
		return customerFullName;
	}

	public void setCustomerFullName(String customerFullName) {
		this.customerFullName = customerFullName;
	}

	public String getResponsiblePersonOfCompanyInn() {
		return responsiblePersonOfCompanyInn;
	}

	public void setResponsiblePersonOfCompanyInn(String responsiblePersonOfCompanyInn) {
		this.responsiblePersonOfCompanyInn = responsiblePersonOfCompanyInn;
	}

	public String getResponsiblePersonOfCompanyOrganizationInn() {
		return responsiblePersonOfCompanyOrganizationInn;
	}

	public void setResponsiblePersonOfCompanyOrganizationInn(String responsiblePersonOfCompanyOrganizationInn) {
		this.responsiblePersonOfCompanyOrganizationInn = responsiblePersonOfCompanyOrganizationInn;
	}

	public String getResponsiblePersonOfCompanyFullName() {
		return responsiblePersonOfCompanyFullName;
	}

	public void setResponsiblePersonOfCompanyFullName(String responsiblePersonOfCompanyFullName) {
		this.responsiblePersonOfCompanyFullName = responsiblePersonOfCompanyFullName;
	}
}
