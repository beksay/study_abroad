package org.infosystema.study_abroad.model.docs;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="interview_date")
public class VisaStep extends Module {

	private static final long serialVersionUID = 1L;

	 private Set<Visa> visas;
	
	@OneToMany(mappedBy="module")
	public Set<Visa> getVisas() {
		return visas;
	}
	
	public void setVisas(Set<Visa> visas) {
		this.visas = visas;
	}
}
