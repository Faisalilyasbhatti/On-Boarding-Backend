package com.evantagesoft.entities.account;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="account")
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String fullName;
    private String mobileNo;
    private int status;
	private String email;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }
    @PrePersist
    public void setCreatedDate() {
        this.createdDate = new Date();
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
