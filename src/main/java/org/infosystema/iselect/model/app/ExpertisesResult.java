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

import org.infosystema.iselect.enums.ResultStatus;
import org.infosystema.iselect.model.AbstractEntity;
import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.Subdivision;


@Entity
@Access(AccessType.PROPERTY)
@Table(name="expertises_result")
public class ExpertisesResult extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private ExpertisesGoods expertisesGoods;
	private BigDecimal goodsAmount;
	private Subdivision origin;
	private String fromWhom;
	private Dictionary measurementUnit;
	private Date date;
	private ResultStatus status;
	private String protocol;

	@ManyToOne
	@JoinColumn(name="measurement_unit")
	public Dictionary getMeasurementUnit() {
		return measurementUnit;
	}
	
	public void setMeasurementUnit(Dictionary measurementUnit) {
		this.measurementUnit = measurementUnit;
	}

	@ManyToOne
	@JoinColumn(name="expertises_goods")
	public ExpertisesGoods getExpertisesGoods() {
		return expertisesGoods;
	}
	
	public void setExpertisesGoods(ExpertisesGoods expertisesGoods) {
		this.expertisesGoods = expertisesGoods;
	}

	@Column(name="goods_amount")
	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	@ManyToOne
	@JoinColumn(name="origin_id")
	public Subdivision getOrigin() {
		return origin;
	}
	
	public void setOrigin(Subdivision origin) {
		this.origin = origin;
	}

	@Column(name="from_whom")
	public String getFromWhom() {
		return fromWhom;
	}

	public void setFromWhom(String fromWhom) {
		this.fromWhom = fromWhom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Enumerated(EnumType.ORDINAL)
	public ResultStatus getStatus() {
		return status;
	}

	public void setStatus(ResultStatus status) {
		this.status = status;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

}