package org.infosystema.study_abroad.model.docs;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;



/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="visa_document")
public class VisaDocument extends Module{
	private static final long serialVersionUID = 1L;

    private Set<Documents> documents;
	
	@OneToMany(mappedBy="module")
	public Set<Documents> getDocuments() {
		return documents;
	}
	
	public void setDocuments(Set<Documents> documents) {
		this.documents = documents;
	}
	
}