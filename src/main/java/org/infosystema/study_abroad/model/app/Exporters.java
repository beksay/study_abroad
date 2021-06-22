package org.infosystema.study_abroad.model.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.infosystema.study_abroad.model.Dictionary;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="exporters")
public class Exporters extends AppModule{
	private static final long serialVersionUID = 1L;
	private Dictionary legalForm;
	private String inn;
	private String okpo;
	private String companyName;
	private String directorName;
	private String legalAddress;
	private Dictionary country;
	private String currentAddress;
	private String contact;
	private String email;
	private Dictionary taxpayer;

	@ManyToOne
	@JoinColumn(name="legal_form")
	public Dictionary getLegalForm() {
		return legalForm;
	}

	public void setLegalForm(Dictionary legalForm) {
		this.legalForm = legalForm;
	}
	
	@ManyToOne
	@JoinColumn(name="taxpayer")
	public Dictionary getTaxpayer() {
		return taxpayer;
	}
	
	public void setTaxpayer(Dictionary taxpayer) {
		this.taxpayer = taxpayer;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getOkpo() {
		return okpo;
	}

	public void setOkpo(String okpo) {
		this.okpo = okpo;
	}

	@Column(name="company_name")
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name="director_name")
	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	@Column(name="legal_address")
	public String getLegalAddress() {
		return legalAddress;
	}

	public void setLegalAddress(String legalAddress) {
		this.legalAddress = legalAddress;
	}

	@Column(name="current_address")
	public String getCurrentAddress() {
		return currentAddress;
	}

	public void setCurrentAddress(String currentAddress) {
		this.currentAddress = currentAddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@ManyToOne
	@JoinColumn(name="country")
	public Dictionary getCountry() {
		return country;
	}

	public void setCountry(Dictionary country) {
		this.country = country;
	}

	
	
}