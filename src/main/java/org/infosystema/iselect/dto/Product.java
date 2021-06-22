package org.infosystema.iselect.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.PROPERTY)
public class Product {

	private String name;
	private String unit;
	private String code;
	private boolean isProduct;
	
	public Product() {}
	
	public Product(String name, String unit, String code, boolean isProduct) {
		setCode(code);
		setName(name);
		setProduct(isProduct);
		setUnit(unit);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@XmlElement(name="is_prodict")
	public boolean isProduct() {
		return isProduct;
	}

	public void setProduct(boolean isProduct) {
		this.isProduct = isProduct;
	}
	
}
