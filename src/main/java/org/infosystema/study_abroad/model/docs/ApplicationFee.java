package org.infosystema.study_abroad.model.docs;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="application_fee")
public class ApplicationFee extends Module {

	private static final long serialVersionUID = 1L;
	
	private Set<MoneySimulation> applicationFees;
	
	@OneToMany(mappedBy="module")
	public Set<MoneySimulation> getApplicationFees() {
		return applicationFees;
	}
	 
	public void setApplicationFees(Set<MoneySimulation> applicationFees) {
		this.applicationFees = applicationFees;
	}
	
}
