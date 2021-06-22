package org.infosystema.study_abroad.model.app;

import java.util.Date;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.study_abroad.enums.DisinfectionStatus;
import org.infosystema.study_abroad.enums.ExpertisesStatus;
import org.infosystema.study_abroad.enums.InspectionStatus;
import org.infosystema.study_abroad.model.AbstractEntity;
import org.infosystema.study_abroad.model.Dictionary;
import org.infosystema.study_abroad.model.User;
import org.infosystema.study_abroad.model.nomenclature.EntryPoint;
import org.infosystema.study_abroad.model.nomenclature.TransportType;


@Entity
@Access(AccessType.PROPERTY)
@Table(name="inspection")
public class Inspection extends AbstractEntity<Integer>  {
	private static final long serialVersionUID = 1L;
	private String actNumber;
	private Date actDate;
	private String vehicleNumber;
	private TransportType transportType;
	private String owner;
	private String transporter;
    private Applications applications;
    private InspectionStatus status;
    private ExpertisesStatus expertisesStatus;
    private DisinfectionStatus disinfectionStatus;
    private User user;
    private Set<EntryPoint> entryPoints;
    private Dictionary marking;

	@Column(name = "act_number")
	public String getActNumber() {
		return actNumber;
	}

	public void setActNumber(String actNumber) {
		this.actNumber = actNumber;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date_number")
	public Date getActDate() {
		return actDate;
	}

	public void setActDate(Date actDate) {
		this.actDate = actDate;
	}

	@Column(name = "vehicle_number")
	public String getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(String vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@ManyToOne
	@JoinColumn(name="applications")
	public Applications getApplications() {
		return applications;
	}

	public void setApplications(Applications applications) {
		this.applications = applications;
	}

	@Enumerated(EnumType.ORDINAL)
	public InspectionStatus getStatus() {
		return status;
	}

	public void setStatus(InspectionStatus status) {
		this.status = status;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "expertises_status")
	public ExpertisesStatus getExpertisesStatus() {
		return expertisesStatus;
	}

	public void setExpertisesStatus(ExpertisesStatus expertisesStatus) {
		this.expertisesStatus = expertisesStatus;
	}

	@Column(name = "disinfection_status")
	public DisinfectionStatus getDisinfectionStatus() {
		return disinfectionStatus;
	}

	public void setDisinfectionStatus(DisinfectionStatus disinfectionStatus) {
		this.disinfectionStatus = disinfectionStatus;
	}

	@ManyToOne
	@JoinColumn(name="transport_type")
	public TransportType getTransportType() {
		return transportType;
	}

	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}

	public String getTransporter() {
		return transporter;
	}

	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "inspection_entry_points", 
	  joinColumns = @JoinColumn(name = "inspection_id"), 
	  inverseJoinColumns = @JoinColumn(name = "entry_point_id"))	
	public Set<EntryPoint> getEntryPoints() {
		return entryPoints;
	}

	public void setEntryPoints(Set<EntryPoint> entryPoints) {
		this.entryPoints = entryPoints;
	}

	@ManyToOne
	@JoinColumn(name="marking")
	public Dictionary getMarking() {
		return marking;
	}

	public void setMarking(Dictionary marking) {
		this.marking = marking;
	}

}