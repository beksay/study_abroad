package org.infosystema.iselect.model.app;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.infosystema.iselect.model.AbstractEntity;
import org.infosystema.iselect.model.Dictionary;


@Entity
@Access(AccessType.PROPERTY)
@Table(name="disinfection_result")
public class DisinfectionResult extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private Disinfection disinfection;
	private Date dateFrom;
	private Date dateTo;
	private BigDecimal goodsAmount;
    private String disinfectionPlace;
    private String disinfectionMethod;
    private Dictionary chemical;
	private String concentration;
	private String temperature;
	private String exposition;
	private String degassing;
	private String otherInfo;

	@ManyToOne
	@JoinColumn(name="disinfection")
	public Disinfection getDisinfection() {
		return disinfection;
	}

	public void setDisinfection(Disinfection disinfection) {
		this.disinfection = disinfection;
	}

	@Column(name="date_from")
	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	@Column(name="date_to")
	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	@Column(name="goods_amount")
	public BigDecimal getGoodsAmount() {
		return goodsAmount;
	}

	public void setGoodsAmount(BigDecimal goodsAmount) {
		this.goodsAmount = goodsAmount;
	}

	@Column(name="disinfection_place")
	public String getDisinfectionPlace() {
		return disinfectionPlace;
	}

	public void setDisinfectionPlace(String disinfectionPlace) {
		this.disinfectionPlace = disinfectionPlace;
	}

	@Column(name="disinfection_method")
	public String getDisinfectionMethod() {
		return disinfectionMethod;
	}

	public void setDisinfectionMethod(String disinfectionMethod) {
		this.disinfectionMethod = disinfectionMethod;
	}

	@ManyToOne
	@JoinColumn(name="chemical_id")
	public Dictionary getChemical() {
		return chemical;
	}

	public void setChemical(Dictionary chemical) {
		this.chemical = chemical;
	}

	public String getConcentration() {
		return concentration;
	}

	public void setConcentration(String concentration) {
		this.concentration = concentration;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getExposition() {
		return exposition;
	}

	public void setExposition(String exposition) {
		this.exposition = exposition;
	}

	public String getDegassing() {
		return degassing;
	}

	public void setDegassing(String degassing) {
		this.degassing = degassing;
	}

	@Column(name="other_info")
	public String getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

}