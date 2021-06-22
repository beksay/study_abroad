package org.infosystema.study_abroad.model.app;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import org.infosystema.study_abroad.enums.ModuleStatus;
import org.infosystema.study_abroad.model.AbstractEntity;


/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AppModule extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;
	
	private Date date;
	private Applications applications;
	private Integer index;
	private ModuleStatus status;
	
	@PrePersist
	public void prePersist() {
		date = new Date();
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public Integer getIndex() {
		return index;
	}
	
	public void setIndex(Integer index) {
		this.index = index;
	}
	
	@Enumerated(EnumType.ORDINAL)
	public ModuleStatus getStatus() {
		return status;
	}
	
	public void setStatus(ModuleStatus status) {
		this.status = status;
	}
	
	@ManyToOne
	@JoinColumn(name="app_id")
	public Applications getApplications() {
		return applications;
	}
	
	public void setApplications(Applications applications) {
		this.applications = applications;
	}
	
}
