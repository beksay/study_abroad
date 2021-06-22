package org.infosystema.iselect.model.app;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.iselect.enums.ExpertisesStatus;
import org.infosystema.iselect.model.AbstractEntity;
import org.infosystema.iselect.model.Subdivision;


@Entity
@Access(AccessType.PROPERTY)
@Table(name="expertises")
public class Expertises extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private Date date;
	private String samplingLocation;
	private Inspection inspection;
	private ExpertisesStatus status;
	private Subdivision laboratory;

	@Temporal(TemporalType.DATE)
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}

	@ManyToOne
	@JoinColumn(name="inspection")
	public Inspection getInspection() {
		return inspection;
	}
	
	public void setInspection(Inspection inspection) {
		this.inspection = inspection;
	}

	@Column(name = "sampling_location")
	public String getSamplingLocation() {
		return samplingLocation;
	}

	public void setSamplingLocation(String samplingLocation) {
		this.samplingLocation = samplingLocation;
	}

	@Enumerated(EnumType.ORDINAL)
	public ExpertisesStatus getStatus() {
		return status;
	}

	public void setStatus(ExpertisesStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="laboratory")
	public Subdivision getLaboratory() {
		return laboratory;
	}

	public void setLaboratory(Subdivision laboratory) {
		this.laboratory = laboratory;
	}
}