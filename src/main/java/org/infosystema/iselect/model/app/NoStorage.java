package org.infosystema.iselect.model.app;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.iselect.model.AbstractEntity;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "no_storage")
public class NoStorage extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;

	private String cardNumber;
	private Date regDate;
	private String address;
	private String examiner;
	private String culture;
	private String organism;
	private Double area;
	private Double examArea;
	private Date dateFrom;
	private Date dateTo;

	@Column(name = "card_number")
	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "reg_date")
	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getExaminer() {
		return examiner;
	}

	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}

	public String getCulture() {
		return culture;
	}

	public void setCulture(String culture) {
		this.culture = culture;
	}

	public String getOrganism() {
		return organism;
	}

	public void setOrganism(String organism) {
		this.organism = organism;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	@Column(name = "exam_area")
	public Double getExamArea() {
		return examArea;
	}

	public void setExamArea(Double examArea) {
		this.examArea = examArea;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_from")
	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_to")
	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((culture == null) ? 0 : culture.hashCode());
		result = prime * result + ((dateFrom == null) ? 0 : dateFrom.hashCode());
		result = prime * result + ((dateTo == null) ? 0 : dateTo.hashCode());
		result = prime * result + ((examArea == null) ? 0 : examArea.hashCode());
		result = prime * result + ((examiner == null) ? 0 : examiner.hashCode());
		result = prime * result + ((organism == null) ? 0 : organism.hashCode());
		result = prime * result + ((regDate == null) ? 0 : regDate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		NoStorage other = (NoStorage) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (cardNumber == null) {
			if (other.cardNumber != null)
				return false;
		} else if (!cardNumber.equals(other.cardNumber))
			return false;
		if (culture == null) {
			if (other.culture != null)
				return false;
		} else if (!culture.equals(other.culture))
			return false;
		if (dateFrom == null) {
			if (other.dateFrom != null)
				return false;
		} else if (!dateFrom.equals(other.dateFrom))
			return false;
		if (dateTo == null) {
			if (other.dateTo != null)
				return false;
		} else if (!dateTo.equals(other.dateTo))
			return false;
		if (examArea == null) {
			if (other.examArea != null)
				return false;
		} else if (!examArea.equals(other.examArea))
			return false;
		if (examiner == null) {
			if (other.examiner != null)
				return false;
		} else if (!examiner.equals(other.examiner))
			return false;
		if (organism == null) {
			if (other.organism != null)
				return false;
		} else if (!organism.equals(other.organism))
			return false;
		if (regDate == null) {
			if (other.regDate != null)
				return false;
		} else if (!regDate.equals(other.regDate))
			return false;
		return true;
	}

}