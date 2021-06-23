package org.infosystema.study_abroad.model.app;

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

import org.infosystema.study_abroad.enums.ExpertisesStatus;
import org.infosystema.study_abroad.model.AbstractEntity;


@Entity
@Access(AccessType.PROPERTY)
@Table(name="expertises")
public class Expertises extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private Date date;
	private String samplingLocation;
	private Inspection inspection;
	private ExpertisesStatus status;

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

}