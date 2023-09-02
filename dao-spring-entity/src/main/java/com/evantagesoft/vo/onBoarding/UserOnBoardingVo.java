package com.evantagesoft.vo.onBoarding;


import com.evantagesoft.entities.onBording.FormData;
import com.evantagesoft.entities.onBording.UserOnBoarding;
import com.evantagesoft.entities.user.role.Role;
import com.evantagesoft.vo.user.role.RoleVo;

import java.util.Date;
import java.util.List;

public class UserOnBoardingVo {

    private Long userID;
    private String firstName;
    private String middleName;
    private String lastName;
    private String emailAddress;
    private Date dateOfBirth;
    private String cellPhoneNum;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String idDocNum;
    private int status = 1;
    private String password;
    private List<FormData> formData;
    private Date updatedDate;
    private Date createdDate;
    private UserOnBoarding createdBy;

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
    public String getMiddleName() {
        return middleName;
    }
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
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
    public List<FormData> getFormData() {
        return formData;
    }
    public void setFormData(List<FormData> formData) {
        this.formData = formData;
    }
    public Date getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public UserOnBoarding getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(UserOnBoarding createdBy) {
        this.createdBy = createdBy;
    }


    public static UserOnBoarding getOnBoarding(UserOnBoardingVo userOnBoardingVo) {
        UserOnBoarding user=null;
        if(userOnBoardingVo != null){
            user= new UserOnBoarding();
            user.setUserID(userOnBoardingVo.getUserID());
            user.setFirstName(userOnBoardingVo.getFirstName());
            user.setMiddleName(userOnBoardingVo.getMiddleName());
            user.setLastName(userOnBoardingVo.getLastName());
            user.setEmailAddress(userOnBoardingVo.getEmailAddress());
            user.setDateOfBirth(userOnBoardingVo.getDateOfBirth());
            user.setCellPhoneNum(userOnBoardingVo.getCellPhoneNum());
            user.setAddressLine1(userOnBoardingVo.getAddressLine1());
            user.setAddressLine2(userOnBoardingVo.getAddressLine2());
            user.setCity(userOnBoardingVo.getCity());
            user.setState(userOnBoardingVo.getState());
            user.setCountry(userOnBoardingVo.getCountry());
            user.setCity(userOnBoardingVo.getCity());
            user.setZipcode(userOnBoardingVo.getZipcode());
            user.setIdDocNum(userOnBoardingVo.getIdDocNum());
            user.setStatus(userOnBoardingVo.getStatus());
            user.setPassword(userOnBoardingVo.getPassword());
            user.setCreatedBy(userOnBoardingVo.getCreatedBy());
            user.setCreatedDate(userOnBoardingVo.getCreatedDate());
            user.setUpdatedDate(userOnBoardingVo.getUpdatedDate());
        }
            return user;
    }

}
