package org.infosystema.study_abroad.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "setting")
public class Setting extends AbstractEntity<Integer> {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
    private Date dateTPRegistration;
    private Integer counter;
    private Integer size;
	
    @Column(name="date_tp_registration")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDateTPRegistration() {
		return dateTPRegistration;
	}
    
    public void setDateTPRegistration(Date dateTPRegistration) {
		this.dateTPRegistration = dateTPRegistration;
	}
    
    public Integer getCounter() {
		return counter;
	}
    
    public void setCounter(Integer counter) {
		this.counter = counter;
	}
    
    public Integer getSize() {
		return size;
	}
    
    public void setSize(Integer size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Setting [id=" + id + ", dateTPRegistration=" + dateTPRegistration + ", counter=" + counter + ", size=" + size + "]";
	}
}
