package org.infosystema.study_abroad.model.app;

import java.math.BigDecimal;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.infosystema.study_abroad.enums.GoodStatus;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Dictionary;


@Entity
@Access(AccessType.PROPERTY)
@Table(name="expertises_goods")
public class ExpertisesGoods extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private Goods goods;
	private BigDecimal amount;
	private Dictionary measurementUnit;
	private Expertises expertises;
	private GoodStatus status;

	@ManyToOne
	@JoinColumn(name="expertises")
	public Expertises getExpertises() {
		return expertises;
	}
	
	public void setExpertises(Expertises expertises) {
		this.expertises = expertises;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
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
	@JoinColumn(name="goods")
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	@Enumerated(EnumType.ORDINAL)
	public GoodStatus getStatus() {
		return status;
	}

	public void setStatus(GoodStatus status) {
		this.status = status;
	}

}