package com.evantagesoft.entities.customer;
import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.business.Business;
import com.evantagesoft.entities.business.CustomerBusiness;
import com.evantagesoft.vo.business.BusinessVo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "firstName")
    private String firstName;
    @Column(name = "lastName")
    private String lastName;
    @Column(name = "nationality")
    private String nationality;
    
    @Column(name = "designation")
    private String designation;
    
	@OneToOne
    @JoinColumn(name = "account", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<CustomerAddress> customerAddresses;

    @OneToMany(mappedBy = "customer", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<CustomerDocument> customerDocuments;

    @OneToMany(mappedBy="customer")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference(value="customers")
    private List<CustomerBusiness> businesses;

    @Column(name = "gender")
    private String gender;
    @Column(name = "status")
    private int status;
    @Column(name = "dateofBirth")
    private Date dateOfBirth;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateofBirth) {
        this.dateOfBirth = dateofBirth;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public List<CustomerAddress> getCustomerAddresses() {
        return customerAddresses;
    }
    public void setCustomerAddresses(List<CustomerAddress> customerAddresses) {
        this.customerAddresses = customerAddresses;
    }
    public List<CustomerDocument> getCustomerDocuments() {
        return customerDocuments;
    }
    public void setCustomerDocuments(List<CustomerDocument> customerDocuments) {
        this.customerDocuments = customerDocuments;
    }
    public List<CustomerBusiness> getBusinesses() {
        return businesses;
    }
    public void setBusinesses(List<CustomerBusiness> businesses) {
        this.businesses = businesses;
    }
    @PrePersist
    public void setCreatedDate( ) {
        this.createdDate = new Date();
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getUpdatedDate() {
        return updatedDate;
    }
    @PreUpdate
    public void setUpdatedDate( ) {
        this.updatedDate =new Date();
    }
}
