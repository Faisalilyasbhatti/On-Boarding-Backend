package com.evantagesoft.entities.business;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="business_document")
public class BusinessDocument {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "type")
    private String type;
    @Column(name = "identity")
    private String identity;
    @Column(name = "document")
    private String document;
    @Column(name = "status")
    private int status;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "business", referencedColumnName = "id")
    @JsonBackReference
    private Business business;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getIdentity() {
        return identity;
    }
    public void setIdentity(String identity) {
        this.identity = identity;
    }
    public String getDocument() {
        return document;
    }
    public void setDocument(String document) {
        this.document = document;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public Business getBusiness() {
        return business;
    }
    public void setBusiness(Business business) {
        this.business = business;
    }
    @PrePersist
    public void setCreatedDate() {
        this.createdDate = new Date();
    }
    public Date getUpdatedDate() {
        return updatedDate;
    }
    @PreUpdate
    public void setUpdatedDate() {
        this.updatedDate = new Date();
    }

}
