package org.infosystema.iselect.beans;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@XmlRootElement(name="Currency")
public class CurrencyRateBean {
	
	private String code;
	private Integer nominal;
	private String value;
	
	public CurrencyRateBean() {}

	@XmlAttribute(name="ISOCode")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElement(name="Nominal")
	public Integer getNominal() {
		return nominal;
	}
	
	public void setNominal(Integer nominal) {
		this.nominal = nominal;
	}

	@XmlElement(name="Value")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Currency [code=" + code + ", nominal=" + nominal + ", value="
				+ value + "]";
	}

}

