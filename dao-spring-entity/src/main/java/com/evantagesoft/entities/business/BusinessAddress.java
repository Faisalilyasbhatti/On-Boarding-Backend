package com.evantagesoft.entities.business;
import com.evantagesoft.entities.customer.Customer;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="business_address")
public class BusinessAddress {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "line1")
    private String line1;
    @Column(name = "line2")
    private String line2;
    @Column(name = "city")
    private String city;
    @Column(name = "postalCode")
    private String postalCode;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "business", referencedColumnName = "id")
    @JsonBackReference
    private Business business;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLine1() {
        return line1;
    }
    public void setLine1(String line1) {
        this.line1 = line1;
    }
    public String getLine2() {
        return line2;
    }
    public void setLine2(String line2) {
        this.line2 = line2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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
    public Business getBusiness() {
        return business;
    }
    public void setBusiness(Business business) {
        this.business = business;
    }

}
