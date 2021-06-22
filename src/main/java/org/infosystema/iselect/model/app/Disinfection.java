package org.infosystema.iselect.model.app;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.iselect.enums.DisinfectionStatus;
import org.infosystema.iselect.model.AbstractEntity;
import org.infosystema.iselect.model.Dictionary;


@Entity
@Access(AccessType.PROPERTY)
@Table(name="disinfection")
public class Disinfection extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private Date date;
	private BigDecimal productVolume;
	private BigDecimal totalAmount;
	private Inspection inspection;
	private DisinfectionStatus status;
	private Dictionary measurementUnit;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name="inspection")
	public Inspection getInspection() {
		return inspection;
	}
	
	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	@Column(name = "product_volume")
	public BigDecimal getProductVolume() {
		return productVolume;
	}

	public void setProductVolume(BigDecimal productVolume) {
		this.productVolume = productVolume;
	}

	@Column(name = "total_amount")
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Enumerated(EnumType.ORDINAL)
	public DisinfectionStatus getStatus() {
		return status;
	}

	public void setStatus(DisinfectionStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="measurement_unit")
	public Dictionary getMeasurementUnit() {
		return measurementUnit;
	}
	
	public void setMeasurementUnit(Dictionary measurementUnit) {
		this.measurementUnit = measurementUnit;
	}
}