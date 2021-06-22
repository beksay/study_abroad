package org.infosystema.study_abroad.model;


import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "log")
public class LogEntity extends AbstractEntity<Long>  {

	private static final long serialVersionUID = 1L;

    LocalDateTime createdAt = LocalDateTime.now();
    Integer accountId;
    String before;
    String after;
    
    @Column(name = "created_at")
    public LocalDateTime getCreatedAt() {
		return createdAt;
	}
    
    public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
    
    @Column(name = "account_id")
    public Integer getAccountId() {
		return accountId;
	}
    
    public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
    
    @Column(name = "before_json")
    public String getBefore() {
		return before;
	}
    
    public void setBefore(String before) {
		this.before = before;
	}
    
    @Column(name = "after_json")
    public String getAfter() {
		return after;
	}
    
    public void setAfter(String after) {
		this.after = after;
	}
}
