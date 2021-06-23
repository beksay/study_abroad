package org.infosystema.study_abroad.model.app;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Dictionary;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="goods")
public class Goods extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String number;
	private String description;
	private Dictionary country;
	private Dictionary oblast;
	private BigDecimal weight;
	private Dictionary measurementUnit;
	private BigDecimal packages;
	private Dictionary packageTypes;
	private BigDecimal packagesExtra;
	private Dictionary packageTypesExtra;
	private GoodStep module;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToOne
	@JoinColumn(name="country")
	public Dictionary getCountry() {
		return country;
	}

	public void setCountry(Dictionary country) {
		this.country = country;
	}

	@ManyToOne
	@JoinColumn(name="oblast")
	public Dictionary getOblast() {
		return oblast;
	}

	public void setOblast(Dictionary oblast) {
		this.oblast = oblast;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	@ManyToOne
	@JoinColumn(name="measurement_unit")
	public Dictionary getMeasurementUnit() {
		return measurementUnit;
	}

	public void setMeasurementUnit(Dictionary measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	public BigDecimal getPackages() {
		return packages;
	}

	public void setPackages(BigDecimal packages) {
		this.packages = packages;
	}

	@ManyToOne
	@JoinColumn(name="module_id")
	public GoodStep getModule() {
		return module;
	}
	
	public void setModule(GoodStep module) {
		this.module = module;
	}

	@ManyToOne
	@JoinColumn(name="package_types")
	public Dictionary getPackageTypes() {
		return packageTypes;
	}
	
	public void setPackageTypes(Dictionary packageTypes) {
		this.packageTypes = packageTypes;
	}

	@Column(name = "packages_extra")
	public BigDecimal getPackagesExtra() {
		return packagesExtra;
	}

	public void setPackagesExtra(BigDecimal packagesExtra) {
		this.packagesExtra = packagesExtra;
	}

	@ManyToOne
	@JoinColumn(name="package_types_extra")
	public Dictionary getPackageTypesExtra() {
		return packageTypesExtra;
	}

	public void setPackageTypesExtra(Dictionary packageTypesExtra) {
		this.packageTypesExtra = packageTypesExtra;
	}
	
}