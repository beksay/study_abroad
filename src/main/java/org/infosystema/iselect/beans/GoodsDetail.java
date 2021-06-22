package org.infosystema.iselect.beans;

import java.math.BigDecimal;

public class GoodsDetail {
	
	private String varError;
	private Integer stTax;
	private Integer vatTax;
	private Integer legalPersonGoods;
	private BigDecimal stRate;
	private BigDecimal vatRate;
	private BigDecimal amountVat;
	private BigDecimal amountSt;
	private BigDecimal amount;
	private Integer unit;
	private Integer commodityClassificationFea;
	private String exchangeCode;
	private String goodsName;
	private BigDecimal price;
	private BigDecimal baseCount;
	
	public GoodsDetail(String varError, Integer stTax, Integer vatTax, Integer legalPersonGoods, BigDecimal stRate, BigDecimal vatRate, 
			BigDecimal amountVat, BigDecimal amountSt, BigDecimal amount, Integer unit, Integer commodityClassificationFea) {
		setAmount(amount);
		setAmountSt(amountSt);
		setAmountVat(amountVat);
		setCommodityClassificationFea(commodityClassificationFea);
		setLegalPersonGoods(legalPersonGoods);
		setStRate(stRate);
		setStTax(stTax);
		setUnit(unit);
		setVarError(varError);
		setVatRate(vatRate);
		setVatTax(vatTax);
	}
	
	public GoodsDetail(String varError, Integer stTax, Integer vatTax, Integer legalPersonGoods, BigDecimal stRate, BigDecimal vatRate, 
			BigDecimal amountVat, BigDecimal amountSt, BigDecimal amount, Integer unit, Integer commodityClassificationFea, String exchangeCode,
	 String goodsName, BigDecimal price, BigDecimal baseCount) {
		setAmount(amount);
		setAmountSt(amountSt);
		setAmountVat(amountVat);
		setCommodityClassificationFea(commodityClassificationFea);
		setLegalPersonGoods(legalPersonGoods);
		setStRate(stRate);
		setStTax(stTax);
		setUnit(unit);
		setVarError(varError);
		setVatRate(vatRate);
		setVatTax(vatTax);
		setExchangeCode(exchangeCode);
		setGoodsName(goodsName);
		setBaseCount(baseCount);
		setPrice(price);
	}

	public String getVarError() {
		return varError;
	}

	public void setVarError(String varError) {
		this.varError = varError;
	}

	public Integer getStTax() {
		return stTax;
	}

	public void setStTax(Integer stTax) {
		this.stTax = stTax;
	}

	public Integer getVatTax() {
		return vatTax;
	}

	public void setVatTax(Integer vatTax) {
		this.vatTax = vatTax;
	}

	public Integer getLegalPersonGoods() {
		return legalPersonGoods;
	}

	public void setLegalPersonGoods(Integer legalPersonGoods) {
		this.legalPersonGoods = legalPersonGoods;
	}

	public BigDecimal getStRate() {
		return stRate;
	}

	public void setStRate(BigDecimal stRate) {
		this.stRate = stRate;
	}

	public BigDecimal getVatRate() {
		return vatRate;
	}

	public void setVatRate(BigDecimal vatRate) {
		this.vatRate = vatRate;
	}

	public BigDecimal getAmountVat() {
		return amountVat;
	}

	public void setAmountVat(BigDecimal amountVat) {
		this.amountVat = amountVat;
	}

	public BigDecimal getAmountSt() {
		return amountSt;
	}

	public void setAmountSt(BigDecimal amountSt) {
		this.amountSt = amountSt;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}

	public Integer getCommodityClassificationFea() {
		return commodityClassificationFea;
	}

	public void setCommodityClassificationFea(Integer commodityClassificationFea) {
		this.commodityClassificationFea = commodityClassificationFea;
	}
	
	public String getExchangeCode() {
		return exchangeCode;
	}
	
	public void setExchangeCode(String exchangeCode) {
		this.exchangeCode = exchangeCode;
	}
	
	public String getGoodsName() {
		return goodsName;
	}
	
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getBaseCount() {
		return baseCount;
	}

	public void setBaseCount(BigDecimal baseCount) {
		this.baseCount = baseCount;
	}

}
