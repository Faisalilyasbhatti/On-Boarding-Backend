package com.evantagesoft.vo.customer;

import com.evantagesoft.entities.customer.CustomerAddress;


public class CustomerAddressVo {

    private Long id;
    private String line1;
    private String line2;
    private String city;
    private String postalCode;
    private String state;
    private String country;
    private String customer;

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
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public static CustomerAddress getCustomerAddress(CustomerAddressVo customerAddressVo) {
        CustomerAddress customerAddress = null;
        if (customerAddressVo != null) {
            customerAddress= new CustomerAddress();
            customerAddress.setId(customerAddressVo.getId());
            customerAddress.setLine1(customerAddressVo.getLine1());
            customerAddress.setLine2(customerAddressVo.getLine2());
            customerAddress.setCity(customerAddressVo.getCity());
            customerAddress.setPostalCode(customerAddressVo.getPostalCode());
            customerAddress.setState(customerAddressVo.getState());
            customerAddress.setCountry(customerAddressVo.getCountry());
        }
        return customerAddress;
    }

    public static CustomerAddressVo getCustomerAddressVo(CustomerAddress customerAddress) {
        CustomerAddressVo customerAddressVo = null;
        if (customerAddress != null) {
            customerAddressVo = new CustomerAddressVo();
            customerAddressVo.setId(customerAddress.getId());
            customerAddressVo.setLine1(customerAddress.getLine1());
            customerAddressVo.setLine2(customerAddress.getLine2());
            customerAddressVo.setId(customerAddress.getId());
            customerAddressVo.setCity(customerAddress.getCity());
            customerAddressVo.setPostalCode(customerAddress.getPostalCode());
            customerAddressVo.setState(customerAddress.getState());
            customerAddressVo.setCountry(customerAddress.getCountry());
        }
        return customerAddressVo;
    }
}
