package org.infosystema.iselect.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class BankAccount {

	private String serialNumber;
	private String bankAccountName;
	private String bankAccountNumber;
	private String bankBik;
	private String bankAccountCurrency;
	
	public BankAccount() {}
	
	public BankAccount(String serialNumber, String bankAccountName, String bankAccountNumber, String bankBik, String bankAccountCurrency) {
		setBankAccountName(bankAccountName);
		setBankAccountNumber(bankAccountNumber);
		setBankBik(bankBik);
		setSerialNumber(serialNumber);
		setBankAccountCurrency(bankAccountCurrency);
	}

	public String getBankAccountCurrency() {
		return bankAccountCurrency;
	}
	
	public void setBankAccountCurrency(String bankAccountCurrency) {
		this.bankAccountCurrency = bankAccountCurrency;
	}
	
	public String getSerialNumber() {
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
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

	@Override
	public String toString() {
		return "BankAccount [serialNumber=" + serialNumber + ", bankAccountName=" + bankAccountName + ", bankAccountNumber=" + bankAccountNumber + ", bankBik=" + bankBik
				+ ", bankAccountCurrency=" + bankAccountCurrency + "]";
	}

}
