package com.evantagesoft.vo.business;


import com.evantagesoft.entities.customer.Customer;
import com.evantagesoft.vo.customer.CustomerVo;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

public class CustomerBusinessVo {

    private Long id;
    private BusinessVo business;
    private CustomerVo customer;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public BusinessVo getBusiness() {
        return business;
    }
    public void setBusiness(BusinessVo business) {
        this.business = business;
    }
    public CustomerVo getCustomer() {
        return customer;
    }
    public void setCustomer(CustomerVo customer) {
        this.customer = customer;
    }
}
