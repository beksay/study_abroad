package org.infosystema.study_abroad.model.nomenclature;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.infosystema.study_abroad.enums.MemberType;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Dictionary;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="organization")
public class Organization extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private MemberType memberType;
	private String inn;
	private Dictionary legalForm;
	private String name;
	private String enName;
	private String kgName;
	private String fullname;
	private Dictionary worldClassifier;
	private Dictionary addressRegister;
	private String street;
	private String address; 
	private String contact;
	private String email;
	
	@Enumerated(EnumType.ORDINAL)
	@Column(name="member_type")
	public MemberType getMemberType() {
		return memberType;
	}
	
	public void setMemberType(MemberType memberType) {
		this.memberType = memberType;
	}
	
	@Field(store = Store.YES, analyze = Analyze.NO)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="en_name")
	public String getEnName() {
		return enName;
	}
	
	public void setEnName(String enName) {
		this.enName = enName;
	}
	
	@Column(name="kg_name")
	public String getKgName() {
		return kgName;
	}
	
	public void setKgName(String kgName) {
		this.kgName = kgName;
	}

	public String getInn() {
		return inn;
	}

	public void setInn(String inn) {
		this.inn = inn;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	@ManyToOne
	@JoinColumn(name="world_classifier")
	public Dictionary getWorldClassifier() {
		return worldClassifier;
	}

	public void setWorldClassifier(Dictionary worldClassifier) {
		this.worldClassifier = worldClassifier;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	@JoinColumn(name="legal_form")
	public Dictionary getLegalForm() {
		return legalForm;
	}

	public void setLegalForm(Dictionary legalForm) {
		this.legalForm = legalForm;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@ManyToOne
	@JoinColumn(name="address_register")
	public Dictionary getAddressRegister() {
		return addressRegister;
	}

	public void setAddressRegister(Dictionary addressRegister) {
		this.addressRegister = addressRegister;
	}
	
}