package org.infosystema.study_abroad.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name="accounts")
public class BankAccountData {

	private List<BankAccount> list;
	
	@XmlElement(name="account")
	public List<BankAccount> getList() {
		return list;
	}
	
	public void setList(List<BankAccount> list) {
		this.list = list;
	}
	
}
