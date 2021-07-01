package org.infosystema.study_abroad.model.docs;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.study_abroad.enums.CurrencyType;
import org.infosystema.study_abroad.enums.FeeType;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.User;



/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="money_simulation")
public class MoneySimulation extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private ApplicationFee module;
	private BigDecimal amount;
	private CurrencyType currencyType;
	private FeeType feeType;
	private String note;
	private Date dateCreated;
	private Date dateModify;
	private User user;
	private Person person;
	
	@ManyToOne
	@JoinColumn(name="module_id")
	public ApplicationFee getModule() {
		return module;
	}
	
	public void setModule(ApplicationFee module) {
		this.module = module;
	}
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="fee_type")
	public FeeType getFeeType() {
		return feeType;
	}
	
	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Enumerated(EnumType.ORDINAL)
	@Column(name="currency_type")
	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_created")
	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_modify")
	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne
	@JoinColumn(name="person_id")
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
}