package org.infosystema.study_abroad.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.study_abroad.annotation.Logged;
import org.infosystema.study_abroad.model.docs.Documents;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationDocs extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Documents documents;

	public Documents getDocuments() {
		return documents;
	}
	
	public void setDocuments(Documents documents) {
		this.documents = documents;
	}

}
