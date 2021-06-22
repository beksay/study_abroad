package org.infosystema.iselect.model.app;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.infosystema.iselect.model.Dictionary;
import org.infosystema.iselect.model.nomenclature.EntryPoint;
import org.infosystema.iselect.model.nomenclature.TransportType;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="transportations")
public class Transportations extends AppModule{
	private static final long serialVersionUID = 1L;
	private Dictionary departurePoint;
	private String addressDept;
	private Dictionary addressDeparture;
	private Dictionary destinationPoint;
	private String addressDest;
	private Dictionary addressDestination;
	private Set<EntryPoint> entryPoints;
	private TransportType transportType;
	private String transporter;
	private String tripNumber;	
	private String sealNumber;
	private Dictionary countryExport;
	private Dictionary countryImport;
	private Dictionary countryTransit;
	
	@ManyToOne
	@JoinColumn(name="departure_point")
	public Dictionary getDeparturePoint() {
		return departurePoint;
	}
	
	public void setDeparturePoint(Dictionary departurePoint) {
		this.departurePoint = departurePoint;
	}
	
	@ManyToOne
	@JoinColumn(name="destination_point")
	public Dictionary getDestinationPoint() {
		return destinationPoint;
	}
	
	public void setDestinationPoint(Dictionary destinationPoint) {
		this.destinationPoint = destinationPoint;
	}

	public String getTransporter() {
		return transporter;
	}

	public void setTransporter(String transporter) {
		this.transporter = transporter;
	}

	@Column(name="trip_number")
	public String getTripNumber() {
		return tripNumber;
	}

	public void setTripNumber(String tripNumber) {
		this.tripNumber = tripNumber;
	}

	@Column(name="seal_number")
	public String getSealNumber() {
		return sealNumber;
	}

	public void setSealNumber(String sealNumber) {
		this.sealNumber = sealNumber;
	}

	@ManyToOne
	@JoinColumn(name="country_export")
	public Dictionary getCountryExport() {
		return countryExport;
	}

	public void setCountryExport(Dictionary countryExport) {
		this.countryExport = countryExport;
	}

	@ManyToOne
	@JoinColumn(name="country_import")
	public Dictionary getCountryImport() {
		return countryImport;
	}

	public void setCountryImport(Dictionary countryImport) {
		this.countryImport = countryImport;
	}

	@ManyToOne
	@JoinColumn(name="country_transit")
	public Dictionary getCountryTransit() {
		return countryTransit;
	}

	public void setCountryTransit(Dictionary countryTransit) {
		this.countryTransit = countryTransit;
	}

	@ManyToOne
	@JoinColumn(name="transport_type")
	public TransportType getTransportType() {
		return transportType;
	}

	public void setTransportType(TransportType transportType) {
		this.transportType = transportType;
	}

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
	  name = "transportation_entry_points", 
	  joinColumns = @JoinColumn(name = "transportaion_id"), 
	  inverseJoinColumns = @JoinColumn(name = "entry_point_id"))	
	public Set<EntryPoint> getEntryPoints() {
		return entryPoints;
	}

	public void setEntryPoints(Set<EntryPoint> entryPoints) {
		this.entryPoints = entryPoints;
	}

	@ManyToOne
	@JoinColumn(name="address_departure")
	public Dictionary getAddressDeparture() {
		return addressDeparture;
	}

	public void setAddressDeparture(Dictionary addressDeparture) {
		this.addressDeparture = addressDeparture;
	}

	@ManyToOne
	@JoinColumn(name="address_destination")
	public Dictionary getAddressDestination() {
		return addressDestination;
	}

	public void setAddressDestination(Dictionary addressDestination) {
		this.addressDestination = addressDestination;
	}

	@Column(name = "address_dept")
	public String getAddressDept() {
		return addressDept;
	}

	public void setAddressDept(String addressDept) {
		this.addressDept = addressDept;
	}

	@Column(name = "address_dest")
	public String getAddressDest() {
		return addressDest;
	}

	public void setAddressDest(String addressDest) {
		this.addressDest = addressDest;
	}

	
	
}