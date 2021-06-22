package org.infosystema.iselect.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

public class ReceiptgoodsHeaderDto implements Serializable  {
	
	private static final long serialVersionUID = 5915563627372261924L;
	private Long id;
	private Date createdDate;
	private Date deliveryDate;
	private String number;
	private String ownedCrmReceiptCode;
	private String receiptTypeName;
	private String statusName;
	private String contractorFullName;
	private String invoiceNumber;
	private BigDecimal totalAmount;
	private String note;
	private String documentUUID;
	private Integer statusId;
	private String legalPersonFullName;
	private String receiptTypeCode;
	private Date invoiceDate;
	private Object temp;
	
	public ReceiptgoodsHeaderDto() {}
	
	public ReceiptgoodsHeaderDto(Long id, Date createdDate, Date deliveryDate, String number, String ownedCrmReceiptCode, String receiptTypeName, 
			String statusName, String contractorFullName, String invoiceNumber, BigDecimal totalAmount, String note, String documentUUID, 
			Integer statusId, String legalPersonFullName, String receiptTypeCode, Date invoiceDate) {
		setContractorFullName(contractorFullName);
		setCreatedDate(createdDate);
		setDeliveryDate(deliveryDate);
		setId(id);
		setInvoiceNumber(invoiceNumber);
		setNote(note);
		setNumber(invoiceNumber);
		setOwnedCrmReceiptCode(ownedCrmReceiptCode);
		setReceiptTypeName(receiptTypeName);
		setStatusName(statusName);
		setTotalAmount(totalAmount);
		setDocumentUUID(documentUUID);
		setStatusId(statusId);
		setLegalPersonFullName(legalPersonFullName);
		setReceiptTypeCode(receiptTypeCode);
		setInvoiceDate(invoiceDate);
	}
	
	public Object getTemp() {
		return temp;
	}
	
	public void setTemp(Object temp) {
		this.temp = temp;
	}
	
	public Date getInvoiceDate() {
		return invoiceDate;
	}
	
	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	
	public String getReceiptTypeCode() {
		return receiptTypeCode;
	}
	
	public void setReceiptTypeCode(String receiptTypeCode) {
		this.receiptTypeCode = receiptTypeCode;
	}
	
	public String getLegalPersonFullName() {
		return legalPersonFullName;
	}
	
	public void setLegalPersonFullName(String legalPersonFullName) {
		this.legalPersonFullName = legalPersonFullName;
	}
	
	public Integer getStatusId() {
		return statusId;
	}
	
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	
	public String getDocumentUUID() {
		return documentUUID;
	}
	
	public void setDocumentUUID(String documentUUID) {
		this.documentUUID = documentUUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getOwnedCrmReceiptCode() {
		return ownedCrmReceiptCode;
	}

	public void setOwnedCrmReceiptCode(String ownedCrmReceiptCode) {
		this.ownedCrmReceiptCode = ownedCrmReceiptCode;
	}

	public String getReceiptTypeName() {
		return receiptTypeName;
	}

	public void setReceiptTypeName(String receiptTypeName) {
		this.receiptTypeName = receiptTypeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getContractorFullName() {
		return contractorFullName;
	}

	public void setContractorFullName(String contractorFullName) {
		this.contractorFullName = contractorFullName;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	
	
}
