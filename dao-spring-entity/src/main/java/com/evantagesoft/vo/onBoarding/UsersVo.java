package com.evantagesoft.vo.onBoarding;
import com.evantagesoft.entities.onBording.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UsersVo {
    private static final Logger logger = LoggerFactory.getLogger(UsersVo.class);

    private Long id;
    private String country;
    private String contact;
    private String emailAddress;
    private int isActive;
    private String fullName;
    private Date createdDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getContact() {
        return contact;
    }
    public void setContact(String contact) {
        this.contact = contact;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public int getIsActive() {
        return isActive;
    }
    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // return users object of usersVo
    public static Users getUser(UsersVo usersVo) {
        Users users = null;
        if(usersVo != null) {
        users= new Users();
        users.setId(usersVo.getId());
        users.setEmailAddress(usersVo.getEmailAddress());
        users.setContact(usersVo.getContact());
        users.setFullName(usersVo.getFullName());
        users.setCountry(usersVo.getCountry());
            if(!StringUtils.isEmpty(usersVo.getCreatedDate())) {
                try {
                    users.setCreatedDate(new SimpleDateFormat("dd-MM-yyyy").parse(""+usersVo.getCreatedDate()));
                } catch (Exception e) {
                    logger.error("",e);
                }
            }
            else{
                users.setCreatedDate(new Date());
            }
        users.setIsActive(0);
        }
        return users;
    }

    public static UsersVo getUserVo(Users Users) {
        UsersVo usersVo=null;
        if(Users!= null){
            usersVo= new UsersVo();
            usersVo.setId(Users.getId());
            usersVo.setFullName(Users.getFullName());
            usersVo.setEmailAddress(Users.getEmailAddress());
            usersVo.setContact(Users.getContact());
            usersVo.setCountry(Users.getCountry());
            usersVo.setCreatedDate(Users.getCreatedDate());
        }

        return usersVo;
    }
}
