package org.infosystema.study_abroad.model.docs;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="application_submission")
public class ApplicationSubmission extends Module {

	private static final long serialVersionUID = 1L;
	
    private Set<Applications> applications;
	
	@OneToMany(mappedBy="module")
	public Set<Applications> getApplications() {
		return applications;
	}
	
	public void setApplications(Set<Applications> applications) {
		this.applications = applications;
	}
	
}
