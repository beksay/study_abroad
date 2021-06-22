package org.infosystema.iselect.model;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.infosystema.iselect.enums.LogStatus;

/**
 * 
 * @author Kuttubek Aidaraliev
 *
 */

@Entity
@Access(AccessType.PROPERTY)
@Cacheable(true)
@Table(name = "login_log")
public class LoginLog extends AbstractEntity<Integer> {

    private static final long serialVersionUID = -4532833918223514582L;
    private String ipAddress;
    private User user;
    private Date dateCreated;
    private LogStatus status;

    public LoginLog() {
    }

    @PrePersist
    public void perPersist() {
        dateCreated = new Date();
    }
    
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Column(name="ip_address")
    public String getIpAddress() {
        return ipAddress;
    }


    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    @ManyToOne
    @JoinColumn(name="user_id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Enumerated(EnumType.ORDINAL)
	public LogStatus getStatus() {
		return status;
	}
	
	public void setStatus(LogStatus status) {
		this.status = status;
	}

}