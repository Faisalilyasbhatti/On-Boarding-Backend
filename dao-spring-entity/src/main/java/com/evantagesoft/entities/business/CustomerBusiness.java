package com.evantagesoft.entities.business;


import com.evantagesoft.entities.customer.Customer;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name="customer_business")
public class CustomerBusiness {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "business", referencedColumnName = "id")
    @JsonBackReference(value="business")
    private Business business;

    @ManyToOne()
    @JoinColumn(name = "customer", referencedColumnName = "id")
    @JsonBackReference(value="customers")
    private Customer customer;
    private String type;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Business getBusiness() {
        return business;
    }
    public void setBusiness(Business business) {
        this.business = business;
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
}
