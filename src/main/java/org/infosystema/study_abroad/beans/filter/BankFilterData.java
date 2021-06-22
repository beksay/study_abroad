package org.infosystema.study_abroad.beans.filter;

import java.util.Date;

public class BankFilterData extends FilterData {
	
	private static final long serialVersionUID = 1L;
	
	private String query;
	private Date dateFrom;
	private Date dateTo;
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
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
}