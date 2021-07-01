package org.infosystema.study_abroad.model.docs;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="visa_question")
public class VisaQuestion extends Module {

	private static final long serialVersionUID = 1L;
	
	private Set<Questions> questions;
	
	@OneToMany(mappedBy="module")
	public Set<Questions> getQuestions() {
		return questions;
	}
	
	public void setQuestions(Set<Questions> questions) {
		this.questions = questions;
	}
	
}
