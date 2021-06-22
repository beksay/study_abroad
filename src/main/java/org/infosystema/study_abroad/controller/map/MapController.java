package org.infosystema.study_abroad.controller.map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.infosystema.study_abroad.beans.FilterExample;
import org.infosystema.study_abroad.beans.SortEnum;
import org.infosystema.study_abroad.model.app.MapZone;
import org.infosystema.study_abroad.service.MapZoneService;
import org.infosystema.study_abroad.util.web.Messages;

import com.jsf2leaf.model.Circle;
import com.jsf2leaf.model.LatLong;
import com.jsf2leaf.model.Layer;
import com.jsf2leaf.model.Map;
import com.jsf2leaf.model.Marker;

@Named
@ViewScoped
public class MapController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5762830281907031456L;

	@EJB
	private MapZoneService service;

	@Inject
	private ConversationMap conversation;

	private MapZone mapZone;

	@PostConstruct()
	private void init() {

		if (mapZone == null) {

			mapZone = new MapZone();
		}
	}

	public String add() {

		mapZone = new MapZone();
		conversation.setMapZone(mapZone);

		return form();
	}

	public String edit(MapZone mapZone) {

		this.mapZone = mapZone;
		conversation.setMapZone(mapZone);

		return form();
	}

	public String save() {

		if (mapZone == null) {

			FacesContext.getCurrentInstance().addMessage("form",
					new FacesMessage(FacesMessage.SEVERITY_ERROR, Messages.getMessage("invalidData"), null));
			return null;
		}

		conversation.getMapZone();

		if (conversation.getMapZone().getId() == null) {

			service.persist(conversation.getMapZone());

		} else {

			service.merge(conversation.getMapZone());
		}

		return cancel();
	}

	public String cancel() {

		mapZone = null;
		conversation.setMapZone(null);

		return list();
	}

	private String list() {
		return "/view/map/map_list.xhtml?faces-redirect=true";
	}

	private String form() {
		return "/view/map/map_coordinates.xhtml?faces-redirect=true";
	}

	public Map getFields() {

		Map fieldMap = new Map();

		Layer polycircleLayer = (new Layer()).setLabel("Polyline/Circle");

		for (MapZone zone : getMapList()) {

			polycircleLayer
					.addMarker(new Marker(new LatLong(zone.getLatitude(), zone.getLongitude()),
							zone.getQuarantineObject()))
					.addCircle((new Circle()).setPosition(new LatLong(zone.getLatitude(), zone.getLongitude()))
							.setColor("red").setRadius(zone.getDangerRadius()).setFillColor("red").setWeight(3))
					.addCircle((new Circle()).setPosition(new LatLong(zone.getLatitude(), zone.getLongitude()))
							.setColor("yellow").setFillColor("yellow").setRadius(zone.getWarningRadius()).setWeight(3));
		}

		fieldMap.setWidth("auto").setHeight("800px").setCenter(new LatLong("42.8746", "74.5698")).setZoom(12)
				.setMaxZoom(19).setMinZoom(1).setDraggingEnabled(true).setZoomControl(true);

		fieldMap.addLayer(polycircleLayer).setLayerControl(false);

		return fieldMap;
	}

	public List<MapZone> getMapList() {

		List<FilterExample> examples = new ArrayList<>();

		Long count = service.countByExample(examples);

		return service.findByExample(0, Math.toIntExact(count), SortEnum.ASCENDING, examples, "id");
	}

	public MapZone getMapZone() {
		return mapZone;
	}

	public void setMapZone(MapZone mapZone) {
		this.mapZone = mapZone;
	}

}
