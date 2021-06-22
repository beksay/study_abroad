package org.infosystema.iselect.model.nomenclature;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.infosystema.iselect.model.AbstractEntity;
import org.infosystema.iselect.model.Dictionary;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name="violation_of_law")
public class ViolationOfLaw extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String violationName;
	private Dictionary controlMeasures;
	
	@Column(name="violation_name")
	public String getViolationName() {
		return violationName;
	}
	
	public void setViolationName(String violationName) {
		this.violationName = violationName;
	}

	@ManyToOne
	@JoinColumn(name="control_measures")
	public Dictionary getControlMeasures() {
		return controlMeasures;
	}

	public void setControlMeasures(Dictionary controlMeasures) {
		this.controlMeasures = controlMeasures;
	}

	
	
}