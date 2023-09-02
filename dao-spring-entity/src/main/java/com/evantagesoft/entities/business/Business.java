package com.evantagesoft.entities.business;

import com.evantagesoft.entities.account.Account;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="business")
public class Business {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String registrationNo;
    private String taxNo;
    private String website;
	private String industry;
    private String registrationType;
    private int status;
    
	@OneToOne
    @JoinColumn(name = "account", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "business", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BusinessAddress> businessAddresses;
    @OneToMany(mappedBy = "business", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<BusinessDocument> businessDocuments;

    @OneToMany(mappedBy = "business")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference(value ="business")
    private List<CustomerBusiness> customers;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getRegistrationNo() {
        return registrationNo;
    }
    
    
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getTaxNo() {
        return taxNo;
    }
    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
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
        this.updatedDate = updatedDate;
    }
    public List<BusinessAddress> getBusinessAddresses() {
        return businessAddresses;
    }
    public void setBusinessAddresses(List<BusinessAddress> businessAddresses) {
        this.businessAddresses = businessAddresses;
    }
    public List<BusinessDocument> getBusinessDocuments() {
        return businessDocuments;
    }
    public void setBusinessDocuments(List<BusinessDocument> businessDocuments) {
        this.businessDocuments = businessDocuments;
    }
    public List<CustomerBusiness> getCustomers() {
        return customers;
    }
    public void setCustomers(List<CustomerBusiness> customers) {
        this.customers = customers;
    }
    
    public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
}
