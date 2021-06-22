package org.infosystema.iselect.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name="client")
public class BankAccountClientData {

	private List<BankAccountClient> list;
	
	@XmlElement(name="account")
	public List<BankAccountClient> getList() {
		return list;
	}
	
	public void setList(List<BankAccountClient> list) {
		this.list = list;
	}
	
}
