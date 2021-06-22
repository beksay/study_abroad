package org.infosystema.study_abroad.model.app;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.infosystema.study_abroad.model.AbstractEntity;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "map_zone")
public class MapZone extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;

	private String quarantineObject;
	private String latitude;
	private String longitude;
	private int dangerRadius;
	private int warningRadius;

	@Column(name = "quarantine_object")
	public String getQuarantineObject() {
		return quarantineObject;
	}

	public void setQuarantineObject(String quarantineObject) {
		this.quarantineObject = quarantineObject;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;	
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Column(name = "danger_radius")
	public int getDangerRadius() {
		return dangerRadius;
	}

	public void setDangerRadius(int dangerRadius) {
		this.dangerRadius = dangerRadius;
	}

	@Column(name = "warning_radius")
	public int getWarningRadius() {
		return warningRadius;
	}

	public void setWarningRadius(int warningRadius) {
		this.warningRadius = warningRadius;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + dangerRadius;
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((quarantineObject == null) ? 0 : quarantineObject.hashCode());
		result = prime * result + warningRadius;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		MapZone other = (MapZone) obj;
		if (dangerRadius != other.dangerRadius)
			return false;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (quarantineObject == null) {
			if (other.quarantineObject != null)
				return false;
		} else if (!quarantineObject.equals(other.quarantineObject))
			return false;
		if (warningRadius != other.warningRadius)
			return false;
		return true;
	}

}