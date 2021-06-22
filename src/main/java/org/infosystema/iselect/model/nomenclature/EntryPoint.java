package org.infosystema.iselect.model.nomenclature;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Store;
import org.infosystema.iselect.model.AbstractEntity;
import org.infosystema.iselect.model.Dictionary;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="entry_point")
public class EntryPoint extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String name;
	private String enName;
	private String kgName;
	private Dictionary worldClassifier;
	private Dictionary transportationMethod;
	
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

	@ManyToOne
	@JoinColumn(name="world_classifier")
	public Dictionary getWorldClassifier() {
		return worldClassifier;
	}

	public void setWorldClassifier(Dictionary worldClassifier) {
		this.worldClassifier = worldClassifier;
	}

	@ManyToOne
	@JoinColumn(name="transportation_method")
	public Dictionary getTransportationMethod() {
		return transportationMethod;
	}

	public void setTransportationMethod(Dictionary transportationMethod) {
		this.transportationMethod = transportationMethod;
	}

	
	
}