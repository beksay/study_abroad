package org.infosystema.study_abroad.beans;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@XmlRootElement(name="CurrencyRates")
public class CurrencyRates {
	
	private String name;
	private String date;
	private List<CurrencyRateBean> currencies;
	
	public CurrencyRates() {}

	@XmlAttribute(name="Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name="Date")
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	@XmlElement(name="Currency", required=true)
	public List<CurrencyRateBean> getCurrencies() {
		return currencies;
	}
	
	public void setCurrencies(List<CurrencyRateBean> currencies) {
		this.currencies = currencies;
	}

	@Override
	public String toString() {
		return "CurrencyRates [name=" + name + ", date=" + date
				+ ", currencies=" + currencies + "]";
	}

}

