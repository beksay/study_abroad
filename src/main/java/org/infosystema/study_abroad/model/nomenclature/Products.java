package org.infosystema.study_abroad.model.nomenclature;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Dictionary;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="products")
public class Products extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String name;
	private String enName;
	private String kgName;
	private Dictionary measurementUnit;
	private Dictionary tnvedEaes;
	private Dictionary productType;
	private String botanicalName;
	
	@Field(store = Store.YES, analyze = Analyze.NO)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="en_name")
	public String getEnName() {
		return enName;
	}
	
	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	@Column(name="kg_name")
	public String getKgName() {
		return kgName;
	}
	
	public void setKgName(String kgName) {
		this.kgName = kgName;
	}

	@ManyToOne
	@JoinColumn(name="measurement_unit")
	public Dictionary getMeasurementUnit() {
		return measurementUnit;
	}
	
	public void setMeasurementUnit(Dictionary measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	@ManyToOne
	@JoinColumn(name="tnved_eaes")
	public Dictionary getTnvedEaes() {
		return tnvedEaes;
	}

	public void setTnvedEaes(Dictionary tnvedEaes) {
		this.tnvedEaes = tnvedEaes;
	}

	@ManyToOne
	@JoinColumn(name="product_type")
	public Dictionary getProductType() {
		return productType;
	}

	public void setProductType(Dictionary productType) {
		this.productType = productType;
	}

	@Column(name="botanical_name")
	public String getBotanicalName() {
		return botanicalName;
	}

	public void setBotanicalName(String botanicalName) {
		this.botanicalName = botanicalName;
	}
	
}