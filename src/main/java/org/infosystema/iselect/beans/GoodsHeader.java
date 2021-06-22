package org.infosystema.iselect.beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GoodsHeader {
	
	private String varError;
	private Integer receiptType;
	private Integer bankAccount;
	private Integer contractorLegalPerson;
	private Integer invoceDeliveryType;
	private Integer vatDeliveryType;
	private Integer contractorBankAccount;
	private Integer paymentType;
	private Integer currency;
	private Integer contractorCitizenship;
	private Integer receiptGoodsHeader;
	private Integer receiptCorrectionReason;
	private Integer vat;
	private String exchangeCode;
	private Date createdDate;
	private String ownedCrmReceiptCode;
	private String deliveryContractNumber;
	private Date deliveryContractDate;
	private Boolean isPriceWithoutTaxes;
	private String note;
	private BigDecimal exchangeRate;
	private Boolean isIndustry;
    private BigDecimal openingBalances;
    private BigDecimal assessedContributionsAmount;
    private BigDecimal paidAmount;
    private BigDecimal penaltiesAmount;
    private BigDecimal finesAmount;
    private BigDecimal closingBalances;
    private BigDecimal amountToBePaid;
    private String personalAccountNumber;
    private Boolean isResident;
    private Boolean markGoods;
    private Integer sellerBranchId;
    private String receiptGoodsHeaderNumber;
    private Date correctedReceiptCreationDate;
    private Integer type;
    private BigDecimal totalCost;
    private BigDecimal costWithoutTaxes;
    private String receiptCorrectionSeries;
	private List<GoodsDetail> details = new ArrayList<GoodsDetail>();
	
	public GoodsHeader(String varError, Integer receiptType, Integer bankAccount, Integer contractorLegalPerson, Integer invoceDeliveryType,  Integer vatDeliveryType, 
			Integer contractorBankAccount, Integer paymentType, Integer currency, Integer contractorCitizenship,
			Integer receiptGoodsHeader, Integer receiptCorrectionReason, Integer vat, String exchaneCode, Date createdDate, String ownedCrmReceiptCode, String deliveryContractNumber, 
			Date deliveryContractDate, Boolean isPriceWithoutTaxes, String note, BigDecimal exchangeRate, Boolean isIndustry, BigDecimal openingBalances, BigDecimal assessedContributionsAmount,
			BigDecimal paidAmount, BigDecimal penaltiesAmount, BigDecimal finesAmount, BigDecimal closingBalances, BigDecimal amountToBePaid, String personalAccountNumber, 
			Boolean resident, Boolean markGoods, Integer sellerBranchId, String receiptGoodsHeaderNumber, Date correctedReceiptCreationDate, Integer type, 
			BigDecimal totalCost, BigDecimal costWithoutTaxes, String receiptCorrectionSeries) {
				setBankAccount(bankAccount);
				setContractorBankAccount(contractorBankAccount);
				setContractorCitizenship(contractorCitizenship);
				setContractorLegalPerson(contractorLegalPerson);
				setCurrency(currency);
				setInvoceDeliveryType(invoceDeliveryType);
				setPaymentType(paymentType);
				setReceiptCorrectionReason(receiptCorrectionReason);
				setReceiptGoodsHeader(receiptGoodsHeader);
				setReceiptType(receiptType);
				setVarError(varError);
				setVatDeliveryType(vatDeliveryType);
				setExchangeCode(exchaneCode);
				setCreatedDate(createdDate);
				setOwnedCrmReceiptCode(ownedCrmReceiptCode);
				setDeliveryContractNumber(deliveryContractNumber);
				setDeliveryContractDate(deliveryContractDate);
				setIsPriceWithoutTaxes(isPriceWithoutTaxes);
				setNote(note);
				setExchangeRate(exchangeRate);
				setIsIndustry(isIndustry);
				setOpeningBalances(openingBalances);
				setAssessedContributionsAmount(assessedContributionsAmount);
				setPaidAmount(paidAmount);
				setPenaltiesAmount(penaltiesAmount);
				setFinesAmount(finesAmount);
				setClosingBalances(closingBalances);
				setAmountToBePaid(amountToBePaid);
				setPersonalAccountNumber(personalAccountNumber);
				setVat(vat);
				setIsResident(resident);
				setMarkGoods(markGoods);
				setSellerBranchId(sellerBranchId);
				setReceiptGoodsHeaderNumber(receiptGoodsHeaderNumber);
				setCorrectedReceiptCreationDate(correctedReceiptCreationDate);
				setType(type);
				setTotalCost(totalCost);
				setCostWithoutTaxes(costWithoutTaxes);
				setReceiptCorrectionSeries(receiptCorrectionSeries);
	}
	
	public String getReceiptCorrectionSeries() {
		return receiptCorrectionSeries;
	}
	
	public void setReceiptCorrectionSeries(String receiptCorrectionSeries) {
		this.receiptCorrectionSeries = receiptCorrectionSeries;
	}
	
	public BigDecimal getCostWithoutTaxes() {
		return costWithoutTaxes;
	}
	
	public void setCostWithoutTaxes(BigDecimal costWithoutTaxes) {
		this.costWithoutTaxes = costWithoutTaxes;
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
	public Integer getType() {
		return type;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public String getReceiptGoodsHeaderNumber() {
		return receiptGoodsHeaderNumber;
	}
	
	public void setReceiptGoodsHeaderNumber(String receiptGoodsHeaderNumber) {
		this.receiptGoodsHeaderNumber = receiptGoodsHeaderNumber;
	}
	
	public Date getCorrectedReceiptCreationDate() {
		return correctedReceiptCreationDate;
	}
	
	public void setCorrectedReceiptCreationDate(Date correctedReceiptCreationDate) {
		this.correctedReceiptCreationDate = correctedReceiptCreationDate;
	}
	
	public Integer getSellerBranchId() {
		return sellerBranchId;
	}
	
	public void setSellerBranchId(Integer sellerBranchId) {
		this.sellerBranchId = sellerBranchId;
	}
	
	public Boolean getMarkGoods() {
		return markGoods;
	}
	
	public void setMarkGoods(Boolean markGoods) {
		this.markGoods = markGoods;
	}
	
	public Boolean getIsResident() {
		return isResident;
	}
	
	public void setIsResident(Boolean isResident) {
		this.isResident = isResident;
	}
	
	public Integer getVat() {
		return vat;
	}
	
	public void setVat(Integer vat) {
		this.vat = vat;
	}
	
	public String getExchangeCode() {
		return exchangeCode;
	}
	
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}

	public String getVarError() {
		return varError;
	}

	public void setVarError(String varError) {
		this.varError = varError;
	}

	public Integer getReceiptType() {
		return receiptType;
	}

	public void setReceiptType(Integer receiptType) {
		this.receiptType = receiptType;
	}

	public Integer getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(Integer bankAccount) {
		this.bankAccount = bankAccount;
	}

	public Integer getContractorLegalPerson() {
		return contractorLegalPerson;
	}

	public void setContractorLegalPerson(Integer contractorLegalPerson) {
		this.contractorLegalPerson = contractorLegalPerson;
	}

	public Integer getInvoceDeliveryType() {
		return invoceDeliveryType;
	}

	public void setInvoceDeliveryType(Integer invoceDeliveryType) {
		this.invoceDeliveryType = invoceDeliveryType;
	}

	public Integer getVatDeliveryType() {
		return vatDeliveryType;
	}

	public void setVatDeliveryType(Integer vatDeliveryType) {
		this.vatDeliveryType = vatDeliveryType;
	}

	public Integer getContractorBankAccount() {
		return contractorBankAccount;
	}

	public void setContractorBankAccount(Integer contractorBankAccount) {
		this.contractorBankAccount = contractorBankAccount;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Integer getCurrency() {
		return currency;
	}

	public void setCurrency(Integer currency) {
		this.currency = currency;
	}

	public Integer getContractorCitizenship() {
		return contractorCitizenship;
	}

	public void setContractorCitizenship(Integer contractorCitizenship) {
		this.contractorCitizenship = contractorCitizenship;
	}

	public Integer getReceiptGoodsHeader() {
		return receiptGoodsHeader;
	}

	public void setReceiptGoodsHeader(Integer receiptGoodsHeader) {
		this.receiptGoodsHeader = receiptGoodsHeader;
	}

	public Integer getReceiptCorrectionReason() {
		return receiptCorrectionReason;
	}

	public void setReceiptCorrectionReason(Integer receiptCorrectionReason) {
		this.receiptCorrectionReason = receiptCorrectionReason;
	}
	
	public List<GoodsDetail> getDetails() {
		return details;
	}
	
	public void setDetails(List<GoodsDetail> details) {
		this.details = details;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getOwnedCrmReceiptCode() {
		return ownedCrmReceiptCode;
	}

	public void setOwnedCrmReceiptCode(String ownedCrmReceiptCode) {
		this.ownedCrmReceiptCode = ownedCrmReceiptCode;
	}

	public String getDeliveryContractNumber() {
		return deliveryContractNumber;
	}

	public void setDeliveryContractNumber(String deliveryContractNumber) {
		this.deliveryContractNumber = deliveryContractNumber;
	}

	public Date getDeliveryContractDate() {
		return deliveryContractDate;
	}

	public void setDeliveryContractDate(Date deliveryContractDate) {
		this.deliveryContractDate = deliveryContractDate;
	}

	public Boolean getIsPriceWithoutTaxes() {
		return isPriceWithoutTaxes;
	}

	public void setIsPriceWithoutTaxes(Boolean isPriceWithoutTaxes) {
		this.isPriceWithoutTaxes = isPriceWithoutTaxes;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public BigDecimal getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(BigDecimal exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public Boolean getIsIndustry() {
		return isIndustry;
	}

	public void setIsIndustry(Boolean isIndustry) {
		this.isIndustry = isIndustry;
	}

	public BigDecimal getOpeningBalances() {
		return openingBalances;
	}

	public void setOpeningBalances(BigDecimal openingBalances) {
		this.openingBalances = openingBalances;
	}

	public BigDecimal getAssessedContributionsAmount() {
		return assessedContributionsAmount;
	}

	public void setAssessedContributionsAmount(BigDecimal assessedContributionsAmount) {
		this.assessedContributionsAmount = assessedContributionsAmount;
	}

	public BigDecimal getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(BigDecimal paidAmount) {
		this.paidAmount = paidAmount;
	}

	public BigDecimal getPenaltiesAmount() {
		return penaltiesAmount;
	}

	public void setPenaltiesAmount(BigDecimal penaltiesAmount) {
		this.penaltiesAmount = penaltiesAmount;
	}

	public BigDecimal getFinesAmount() {
		return finesAmount;
	}

	public void setFinesAmount(BigDecimal finesAmount) {
		this.finesAmount = finesAmount;
	}

	public BigDecimal getClosingBalances() {
		return closingBalances;
	}

	public void setClosingBalances(BigDecimal closingBalances) {
		this.closingBalances = closingBalances;
	}

	public BigDecimal getAmountToBePaid() {
		return amountToBePaid;
	}

	public void setAmountToBePaid(BigDecimal amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}

	public String getPersonalAccountNumber() {
		return personalAccountNumber;
	}

	public void setPersonalAccountNumber(String personalAccountNumber) {
		this.personalAccountNumber = personalAccountNumber;
	}
}