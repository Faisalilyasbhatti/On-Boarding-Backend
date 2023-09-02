package com.evantagesoft.entities.auth;
import com.evantagesoft.entities.account.Account;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="otp")
public class Otp {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobileOtp")
    private String mobileOtp;

    @Column(name = "emailOtp")
    private String emailOtp;

    @ManyToOne
    @JoinColumn(name = "account", referencedColumnName = "id")
    private Account account;

    @Column(name = "expiredDate")
    private Date expiredDate;

    @Column(name = "used")
    private int used;

    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMobileOtp() {
        return mobileOtp;
    }
    public void setMobileOtp(String mobileOtp) {
        this.mobileOtp = mobileOtp;
    }
    public String getEmailOtp() {
        return emailOtp;
    }
    public void setEmailOtp(String emailOtp) {
        this.emailOtp = emailOtp;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
    public Date getExpiredDate() {
        return expiredDate;
    }
    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getUsed() {
        return used;
    }
    public void setUsed(int used) {
        this.used = used;
    }

}
