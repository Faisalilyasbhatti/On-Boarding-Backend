package com.evantagesoft.entities.onBording;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="onboarding")
public class UserOnBoarding {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userID;
    
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;
    
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String emailAddress;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @Column(name = "cell_phone_num")
    private String cellPhoneNum;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "country")
    private String country;

    @Column(name = "zipcode")
    private String zipcode;

    @Column(name = "Id_doc_num")
    private String idDocNum;

    @Column(name = "status")
    private int status = 1;
    private String password;

    @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    private List<FormData> formData;

    public void setFormData(List<FormData> formData) {
        this.formData = formData;
    }
    public List<FormData> getFormData() {
        return formData;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @ManyToOne
    @JoinColumn(name="createdBy")
    private UserOnBoarding createdBy;
    
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    
    @ManyToOne
    @JoinColumn(name="updatedBy")
    private UserOnBoarding updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @OneToMany(mappedBy="createdBy", cascade=CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)

    public Long getUserID() {
        return userID;
    }
    public void setUserID(Long userID) {
        this.userID = userID;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public UserOnBoarding getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UserOnBoarding createdBy) {
        this.createdBy = createdBy;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    
    @PrePersist
    public void setCreatedDate() {
        this.createdDate =  new Date();
    }
    public UserOnBoarding getUpdatedBy() {
        return updatedBy;
    }
    public void setUpdatedBy(UserOnBoarding updatedBy) {
        this.updatedBy = updatedBy;
    }
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @PreUpdate
    public void setUpdatedDate() {
        this.updatedDate = new Date();
    }
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getCellPhoneNum() {
        return cellPhoneNum;
    }
    public void setCellPhoneNum(String cellPhoneNum) {
        this.cellPhoneNum = cellPhoneNum;
    }
    public String getAddressLine1() {
        return addressLine1;
    }
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }
    public String getAddressLine2() {
        return addressLine2;
    }
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getZipcode() {
        return zipcode;
    }
    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
    public String getIdDocNum() {
        return idDocNum;
    }
    public void setIdDocNum(String idDocNum) {
        this.idDocNum = idDocNum;
    }
}