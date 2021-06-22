package org.infosystema.study_abroad.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BankAccountClient {

	private String contractorTin;
	private String bankAccountName;
	private String bankAccountNumber;
	private String bankBik;
	private String currencyCode;
	
	public BankAccountClient() {}
	
	public BankAccountClient(String contractorTin, String bankAccountName, String bankAccountNumber, String bankBik, String currencyCode) {
		setBankAccountName(bankAccountName);
		setBankAccountNumber(bankAccountNumber);
		setBankBik(bankBik);
		setContractorTin(contractorTin);
		setCurrencyCode(currencyCode);
	}

	public String getContractorTin() {
		return contractorTin;
	}

	public void setContractorTin(String contractorTin) {
		this.contractorTin = contractorTin;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getBankBik() {
		return bankBik;
	}

	public void setBankBik(String bankBik) {
		this.bankBik = bankBik;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	@Override
	public String toString() {
		return "BankAccountClient [contractorTin=" + contractorTin + ", bankAccountName=" + bankAccountName + ", bankAccountNumber=" + bankAccountNumber + ", bankBik=" + bankBik
				+ ", currencyCode=" + currencyCode + "]";
	}
}
