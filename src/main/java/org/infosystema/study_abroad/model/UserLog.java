package org.infosystema.study_abroad.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.study_abroad.enums.UserOperations;
import org.infosystema.study_abroad.enums.UserStatus;

/**
 * The persistent class for the user log database table.
 * 
 */
@Entity
@Table(name = "user_log")
public class UserLog extends AbstractEntity<Integer> {
	private static final long serialVersionUID = 1L;

	private Role oldRole;
	private String oldUsername;
	private UserStatus oldStatus;
	private Subdivision oldSubdivision;
	private String oldCodes;
	private String oldInn;
	private String oldFullname;
	private String oldEmail;
	private String oldPosition;

	private Role newRole;
	private String newUsername;
	private UserStatus newStatus;
	private Subdivision newSubdivision;
	private String newCodes;
	private String newInn;
	private String newFullname;
	private String newEmail;
	private String newPosition;
	private User userInfo;
	private User userChanged;
	private Date dateChanged;
	private UserOperations action;

	@Enumerated(EnumType.ORDINAL)
	public UserOperations getAction() {
		return action;
	}

	public void setAction(UserOperations action) {
		this.action = action;
	}

	@ManyToOne
	@JoinColumn(name = "old_role_id")
	public Role getOldRole() {
		return oldRole;
	}

	public void setOldRole(Role oldRole) {
		this.oldRole = oldRole;
	}

	@Column(name = "old_username")
	public String getOldUsername() {
		return oldUsername;
	}

	public void setOldUsername(String oldUsername) {
		this.oldUsername = oldUsername;
	}

	@Column(name = "old_status")
	@Enumerated(EnumType.ORDINAL)
	public UserStatus getOldStatus() {
		return oldStatus;
	}

	public void setOldStatus(UserStatus oldStatus) {
		this.oldStatus = oldStatus;
	}

	@ManyToOne
	@JoinColumn(name = "old_subdivision_id")
	public Subdivision getOldSubdivision() {
		return oldSubdivision;
	}

	public void setOldSubdivision(Subdivision oldSubdivision) {
		this.oldSubdivision = oldSubdivision;
	}

	@Column(name = "old_codes")
	public String getOldCodes() {
		return oldCodes;
	}

	public void setOldCodes(String oldCodes) {
		this.oldCodes = oldCodes;
	}

	@Column(name = "old_inn")
	public String getOldInn() {
		return oldInn;
	}

	public void setOldInn(String oldInn) {
		this.oldInn = oldInn;
	}

	@Column(name = "old_fullname")
	public String getOldFullname() {
		return oldFullname;
	}

	public void setOldFullname(String oldFullname) {
		this.oldFullname = oldFullname;
	}

	@Column(name = "old_email")
	public String getOldEmail() {
		return oldEmail;
	}

	public void setOldEmail(String oldEmail) {
		this.oldEmail = oldEmail;
	}

	@Column(name = "old_position")
	public String getOldPosition() {
		return oldPosition;
	}

	public void setOldPosition(String oldPosition) {
		this.oldPosition = oldPosition;
	}

	@ManyToOne
	@JoinColumn(name = "new_role_id")
	public Role getNewRole() {
		return newRole;
	}

	public void setNewRole(Role newRole) {
		this.newRole = newRole;
	}

	@Column(name = "new_username")
	public String getNewUsername() {
		return newUsername;
	}

	public void setNewUsername(String newUsername) {
		this.newUsername = newUsername;
	}

	@Column(name = "new_status")
	@Enumerated(EnumType.ORDINAL)
	public UserStatus getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(UserStatus newStatus) {
		this.newStatus = newStatus;
	}

	@ManyToOne
	@JoinColumn(name = "new_subdivision_id")
	public Subdivision getNewSubdivision() {
		return newSubdivision;
	}

	public void setNewSubdivision(Subdivision newSubdivision) {
		this.newSubdivision = newSubdivision;
	}

	@Column(name = "new_codes")
	public String getNewCodes() {
		return newCodes;
	}

	public void setNewCodes(String newCodes) {
		this.newCodes = newCodes;
	}

	@Column(name = "new_inn")
	public String getNewInn() {
		return newInn;
	}

	public void setNewInn(String newInn) {
		this.newInn = newInn;
	}

	@Column(name = "new_fullname")
	public String getNewFullname() {
		return newFullname;
	}

	public void setNewFullname(String newFullname) {
		this.newFullname = newFullname;
	}

	@Column(name = "new_email")
	public String getNewEmail() {
		return newEmail;
	}

	public void setNewEmail(String newEmail) {
		this.newEmail = newEmail;
	}

	@Column(name = "new_position")
	public String getNewPosition() {
		return newPosition;
	}

	public void setNewPosition(String newPosition) {
		this.newPosition = newPosition;
	}

	@ManyToOne
	@JoinColumn(name = "user_info_id")
	public User getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(User userInfo) {
		this.userInfo = userInfo;
	}

	@ManyToOne
	@JoinColumn(name = "user_changed_id")
	public User getUserChanged() {
		return userChanged;
	}

	public void setUserChanged(User userChanged) {
		this.userChanged = userChanged;
	}

	@Column(name = "date_changed")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDateChanged() {
		return dateChanged;
	}

	public void setDateChanged(Date dateChanged) {
		this.dateChanged = dateChanged;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((dateChanged == null) ? 0 : dateChanged.hashCode());
		result = prime * result + ((newCodes == null) ? 0 : newCodes.hashCode());
		result = prime * result + ((newEmail == null) ? 0 : newEmail.hashCode());
		result = prime * result + ((newFullname == null) ? 0 : newFullname.hashCode());
		result = prime * result + ((newInn == null) ? 0 : newInn.hashCode());
		result = prime * result + ((newPosition == null) ? 0 : newPosition.hashCode());
		result = prime * result + ((newRole == null) ? 0 : newRole.hashCode());
		result = prime * result + ((newStatus == null) ? 0 : newStatus.hashCode());
		result = prime * result + ((newSubdivision == null) ? 0 : newSubdivision.hashCode());
		result = prime * result + ((newUsername == null) ? 0 : newUsername.hashCode());
		result = prime * result + ((oldCodes == null) ? 0 : oldCodes.hashCode());
		result = prime * result + ((oldEmail == null) ? 0 : oldEmail.hashCode());
		result = prime * result + ((oldFullname == null) ? 0 : oldFullname.hashCode());
		result = prime * result + ((oldInn == null) ? 0 : oldInn.hashCode());
		result = prime * result + ((oldPosition == null) ? 0 : oldPosition.hashCode());
		result = prime * result + ((oldRole == null) ? 0 : oldRole.hashCode());
		result = prime * result + ((oldStatus == null) ? 0 : oldStatus.hashCode());
		result = prime * result + ((oldSubdivision == null) ? 0 : oldSubdivision.hashCode());
		result = prime * result + ((oldUsername == null) ? 0 : oldUsername.hashCode());
		result = prime * result + ((userChanged == null) ? 0 : userChanged.hashCode());
		result = prime * result + ((userInfo == null) ? 0 : userInfo.hashCode());
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
		UserLog other = (UserLog) obj;
		if (dateChanged == null) {
			if (other.dateChanged != null)
				return false;
		} else if (!dateChanged.equals(other.dateChanged))
			return false;
		if (newCodes == null) {
			if (other.newCodes != null)
				return false;
		} else if (!newCodes.equals(other.newCodes))
			return false;
		if (newEmail == null) {
			if (other.newEmail != null)
				return false;
		} else if (!newEmail.equals(other.newEmail))
			return false;
		if (newFullname == null) {
			if (other.newFullname != null)
				return false;
		} else if (!newFullname.equals(other.newFullname))
			return false;
		if (newInn == null) {
			if (other.newInn != null)
				return false;
		} else if (!newInn.equals(other.newInn))
			return false;
		if (newPosition == null) {
			if (other.newPosition != null)
				return false;
		} else if (!newPosition.equals(other.newPosition))
			return false;
		if (newRole == null) {
			if (other.newRole != null)
				return false;
		} else if (!newRole.equals(other.newRole))
			return false;
		if (newStatus != other.newStatus)
			return false;
		if (newSubdivision == null) {
			if (other.newSubdivision != null)
				return false;
		} else if (!newSubdivision.equals(other.newSubdivision))
			return false;
		if (newUsername == null) {
			if (other.newUsername != null)
				return false;
		} else if (!newUsername.equals(other.newUsername))
			return false;
		if (oldCodes == null) {
			if (other.oldCodes != null)
				return false;
		} else if (!oldCodes.equals(other.oldCodes))
			return false;
		if (oldEmail == null) {
			if (other.oldEmail != null)
				return false;
		} else if (!oldEmail.equals(other.oldEmail))
			return false;
		if (oldFullname == null) {
			if (other.oldFullname != null)
				return false;
		} else if (!oldFullname.equals(other.oldFullname))
			return false;
		if (oldInn == null) {
			if (other.oldInn != null)
				return false;
		} else if (!oldInn.equals(other.oldInn))
			return false;
		if (oldPosition == null) {
			if (other.oldPosition != null)
				return false;
		} else if (!oldPosition.equals(other.oldPosition))
			return false;
		if (oldRole == null) {
			if (other.oldRole != null)
				return false;
		} else if (!oldRole.equals(other.oldRole))
			return false;
		if (oldStatus != other.oldStatus)
			return false;
		if (oldSubdivision == null) {
			if (other.oldSubdivision != null)
				return false;
		} else if (!oldSubdivision.equals(other.oldSubdivision))
			return false;
		if (oldUsername == null) {
			if (other.oldUsername != null)
				return false;
		} else if (!oldUsername.equals(other.oldUsername))
			return false;
		if (userChanged == null) {
			if (other.userChanged != null)
				return false;
		} else if (!userChanged.equals(other.userChanged))
			return false;
		if (userInfo == null) {
			if (other.userInfo != null)
				return false;
		} else if (!userInfo.equals(other.userInfo))
			return false;
		return true;
	}

}