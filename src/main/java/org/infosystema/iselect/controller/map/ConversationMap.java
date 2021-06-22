package org.infosystema.iselect.controller.map;

import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;

import org.infosystema.iselect.annotation.Logged;
import org.infosystema.iselect.controller.Conversational;
import org.infosystema.iselect.model.app.MapZone;

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
