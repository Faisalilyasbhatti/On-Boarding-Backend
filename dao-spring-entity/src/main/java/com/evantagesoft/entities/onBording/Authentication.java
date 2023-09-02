package com.evantagesoft.entities.onBording;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="user_authentication")
public class Authentication {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userId")
    private int userId;

    @Column(name = "otp")
    private String otp;

    @Column(name = "authType")
    private String authType;

    @Column(name = "used")
    private int used;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
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
}