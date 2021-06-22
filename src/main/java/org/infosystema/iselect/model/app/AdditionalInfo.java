package org.infosystema.iselect.model.app;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Table(name="additional_info")
public class AdditionalInfo extends AppModule{
	private static final long serialVersionUID = 1L;
	private String note;
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}

}