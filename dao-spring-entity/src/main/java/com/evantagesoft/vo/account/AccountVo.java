package com.evantagesoft.vo.account;

import com.evantagesoft.entities.account.Account;
import com.evantagesoft.vo.stakeHolder.StakeHolderVo;

import java.util.Date;

public class AccountVo {

    private Long id;
    private String type;
    private String fullName;
    private String mobileNo;
    private String email;
    private int status;

	private Date createdDate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

    public static Account getAccount(AccountVo accountVo) {
        Account account = null;
        if(accountVo != null) {
            account= new Account();
            account.setId(accountVo.getId());
            account.setEmail(accountVo.getEmail());
            account.setMobileNo(accountVo.getMobileNo());
            account.setFullName(accountVo.getFullName());
            account.setType(accountVo.getType());
            account.setStatus(accountVo.getStatus());
        }
        return account;
    }

    public static AccountVo getAccountVo(Account account) {
        AccountVo accountVo = null;
        if (account != null) {
            accountVo= new AccountVo();
            accountVo.setId(account.getId());
            accountVo.setFullName(account.getFullName());
            accountVo.setEmail(account.getEmail());
            accountVo.setMobileNo(account.getMobileNo());
            accountVo.setType(account.getType());
            accountVo.setCreatedDate(account.getCreatedDate());
            accountVo.setStatus(account.getStatus());
        }
        return accountVo;
    }

    
    public static Account getAccountFromStackHolder(StakeHolderVo stakeHolderVo) {
        Account account = null;
        if(stakeHolderVo != null) {
            account= new Account();
            account.setId(stakeHolderVo.getId());
            account.setEmail(stakeHolderVo.getEmail());
            account.setMobileNo(stakeHolderVo.getMobileNo());
            account.setFullName(stakeHolderVo.getFullName());
            account.setType(stakeHolderVo.getType());
            account.setStatus(stakeHolderVo.getStatus());
        }
        return account;
    }
    
    public static StakeHolderVo getStackHolderVoFromAccount(Account account) {
    	StakeHolderVo stakeHolderVo=null;
    	if(account !=null) {
    		stakeHolderVo= new StakeHolderVo();
    		stakeHolderVo.setId(account.getId());
    		stakeHolderVo.setEmail(account.getEmail());
    		stakeHolderVo.setMobileNo(account.getMobileNo());
    		stakeHolderVo.setFullName(account.getFullName());
    		stakeHolderVo.setType(account.getType());
    		stakeHolderVo.setStatus(account.getStatus());
    	}
    	return stakeHolderVo;
	}
    

    
}
