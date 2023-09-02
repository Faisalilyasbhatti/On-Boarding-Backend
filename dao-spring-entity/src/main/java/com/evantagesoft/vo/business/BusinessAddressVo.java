package com.evantagesoft.vo.business;

import com.evantagesoft.entities.business.BusinessAddress;

public class BusinessAddressVo {

    private Long id;
    private String line1;
    private String line2;
    private String city;
    private String postalCode;
    private String state;
    private String country;
    private BusinessVo business;

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
    public BusinessVo getBusiness() {
        return business;
    }
    public void setBusiness(BusinessVo business) {
        this.business = business;
    }

    public static BusinessAddress getBusinessAddress(BusinessAddressVo businessAddressVo) {
        BusinessAddress businessAddress = null;
        if (businessAddressVo != null) {
            businessAddress= new BusinessAddress();
            businessAddress.setId(businessAddressVo.getId());
            businessAddress.setLine1(businessAddressVo.getLine1());
            businessAddress.setLine2(businessAddressVo.getLine2());
            businessAddress.setCity(businessAddressVo.getCity());
            businessAddress.setPostalCode(businessAddressVo.getPostalCode());
            businessAddress.setState(businessAddressVo.getState());
            businessAddress.setCountry(businessAddressVo.getCountry());
        }
        return businessAddress;
    }

    
    public static BusinessAddressVo getBusinessAddressVo(BusinessAddress businessAddress) {
    	BusinessAddressVo businessAddressVo = null;
        if (businessAddress != null) {
        	businessAddressVo= new BusinessAddressVo();
        	businessAddressVo.setId(businessAddress.getId());
        	businessAddressVo.setLine1(businessAddress.getLine1());
        	businessAddressVo.setLine2(businessAddress.getLine2());
        	businessAddressVo.setCity(businessAddress.getCity());
        	businessAddressVo.setPostalCode(businessAddress.getPostalCode());
        	businessAddressVo.setState(businessAddress.getState());
        	businessAddressVo.setCountry(businessAddress.getCountry());
        }
        return businessAddressVo;
    }
}
