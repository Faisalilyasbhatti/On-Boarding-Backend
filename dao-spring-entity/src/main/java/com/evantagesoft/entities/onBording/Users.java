package com.evantagesoft.entities.onBording;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="users")
public class Users {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "country")
    private String country;

    @Column(name = "contact")
    private String contact;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String emailAddress;

    @Column(name = "isActive")
    private int isActive;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public int getIsActive() {
        return isActive;
    }
    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
