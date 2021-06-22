package org.infosystema.study_abroad.controller.map;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.study_abroad.annotation.Logged;
import org.infosystema.study_abroad.controller.Conversational;
import org.infosystema.study_abroad.model.app.MapZone;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */
@Logged
@Named
@ConversationScoped
public class ConversationMap extends Conversational {

	private static final long serialVersionUID = -6100072166946495229L;

	private MapZone mapZone;

	public MapZone getMapZone() {
		return mapZone;
	}

	public void setMapZone(MapZone mapZone) {
		this.mapZone = mapZone;
	}

}
