package com.evantagesoft.vo.auth;

import com.evantagesoft.entities.onBording.Authentication;
import com.evantagesoft.entities.onBording.Users;
import com.evantagesoft.vo.onBoarding.UsersVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class AuthenticationVo {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationVo.class);

    private Long id;
    private int userId;
    private String otp;
    private String authType;
    private int used;
    private Date otpTime;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }
    public String getAuthType() {
        return authType;
    }
    public void setAuthType(String authType) {
        this.authType = authType;
    }
    public int getUsed() {
        return used;
    }
    public void setUsed(int used) {
        this.used = used;
    }
    public Date getOtpTime() {
        return otpTime;
    }
    public void setOtpTime(Date otpTime) {
        this.otpTime = otpTime;
    }

    public static Authentication getAuth(AuthenticationVo authenticationVo){
        Authentication authentication= null;
        if(authenticationVo!=null){
            authentication= new Authentication();
            authentication.setId(authenticationVo.getId());
            authentication.setUserId(authenticationVo.getUserId());
            authentication.setAuthType(authenticationVo.getAuthType());
            authentication.setUsed(0);
            String otp= new DecimalFormat("0000").format(new Random().nextInt(9999));
            authentication.setOtp(otp);
            if(!StringUtils.isEmpty(authenticationVo.getOtpTime())) {
                try {
                authentication.setOtpTime(new SimpleDateFormat("dd-MM-yyyy").parse(""+authenticationVo.getOtpTime()));
                } catch (Exception e) {
                    logger.error("",e);
                }
            }
            else{
                authentication.setOtpTime(new Date());
            }
        }
        return authentication;
    }

    public static AuthenticationVo getAuthVo(Authentication authentication){
        AuthenticationVo authenticationVo= null;
        if(authentication !=null){
            authenticationVo= new AuthenticationVo();
            authenticationVo.setAuthType(authentication.getAuthType());
            authenticationVo.setId(authentication.getId());
            authenticationVo.setOtp(authentication.getOtp());
            authenticationVo.setUserId(authentication.getUserId());
            authenticationVo.setUsed(authentication.getUsed());
            authenticationVo.setOtpTime(authentication.getOtpTime());
        }
return authenticationVo;
    }

    public static Authentication updateAuth(AuthenticationVo authenticationVo){
        Authentication authentication= null;
        if(authenticationVo!=null){
            authentication= new Authentication();
            authentication.setId(authenticationVo.getId());
            authentication.setUserId(authenticationVo.getUserId());
            authentication.setAuthType(authenticationVo.getAuthType());
            authentication.setUsed(1);
            authentication.setOtp(authenticationVo.getOtp());
            authentication.setOtpTime(authenticationVo.getOtpTime());
        }
        return authentication;
    }

}