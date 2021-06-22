package org.infosystema.study_abroad.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.infosystema.study_abroad.util.web.Constants;

public class GlobalFilter implements Constants, Serializable {
	private static final long serialVersionUID = 7483701216092712572L;
	
	private Date dateFrom;
	private Date dateTo;
	private Date dateFromConfirm;
	private Date dateToConfirm;
	
	private Integer id;
	private BigDecimal amount;

	private Integer currentPage;
	private Integer pageRows;
	private String lastPage;
	
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageRows() {
		return pageRows;
	}
	public void setPageRows(Integer pageRows) {
		this.pageRows = pageRows;
	}
	public String getLastPage() {
		return lastPage;
	}
	public void setLastPage(String lastPage) {
		this.lastPage = lastPage;
	}
	public void clearAll() {
		dateFrom=null;
		dateTo=null;
		dateFromConfirm=null;
		dateToConfirm=null;
		currentPage=null;
		id=null;
		amount=null;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getDateFromConfirm() {
		return dateFromConfirm;
	}
	public void setDateFromConfirm(Date dateFromConfirm) {
		this.dateFromConfirm = dateFromConfirm;
	}
	public Date getDateToConfirm() {
		return dateToConfirm;
	}
	public void setDateToConfirm(Date dateToConfirm) {
		this.dateToConfirm = dateToConfirm;
	}
}
