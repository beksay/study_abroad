package org.infosystema.iselect.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlRootElement(name="products")
public class ProductData {

	private List<Product> list;
	
	@XmlElement(name="product")
	public List<Product> getList() {
		return list;
	}
	
	public void setList(List<Product> list) {
		this.list = list;
	}
	
}
