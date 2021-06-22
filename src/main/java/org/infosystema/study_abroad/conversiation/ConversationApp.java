package org.infosystema.study_abroad.conversiation;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.study_abroad.model.app.AdditionalInfo;
import org.infosystema.study_abroad.model.app.Applications;
import org.infosystema.study_abroad.model.app.Exporters;
import org.infosystema.study_abroad.model.app.Importers;
import org.infosystema.study_abroad.model.app.Transportations;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Named
@ConversationScoped
public class ConversationApp extends Conversational {
	
	private static final long serialVersionUID = -6100072166946495229L;
	
	private Applications applications;
	private Exporters exporters;
	private Importers importers;
	private Transportations transportations;
	private AdditionalInfo additionalInfo;

	public Applications getApplications() {
		return applications;
	}

	public void setApplications(Applications applications) {
		this.applications = applications;
	}

	public Exporters getExporters() {
		return exporters;
	}

	public void setExporters(Exporters exporters) {
		this.exporters = exporters;
	}

	public Importers getImporters() {
		return importers;
	}

	public void setImporters(Importers importers) {
		this.importers = importers;
	}

	public Transportations getTransportations() {
		return transportations;
	}

	public void setTransportations(Transportations transportations) {
		this.transportations = transportations;
	}

	public AdditionalInfo getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(AdditionalInfo additionalInfo) {
		this.additionalInfo = additionalInfo;
	}


}
