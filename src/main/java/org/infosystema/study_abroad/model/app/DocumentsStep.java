package org.infosystema.study_abroad.model.app;

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
@Table(name="documents_step")
public class DocumentsStep extends AppModule{
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