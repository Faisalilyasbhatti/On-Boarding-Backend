package com.evantagesoft.entities.customer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="customer_document")
public class CustomerDocument {

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
    @ManyToOne()
    @JoinColumn(name = "customer", referencedColumnName = "id")
    @JsonBackReference
    private Customer customer;
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
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
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
