package com.evantagesoft.vo.registrationType;

import java.util.Date;

import com.evantagesoft.entities.industry.Industry;
import com.evantagesoft.entities.registrationType.RegistrationType;
import com.evantagesoft.vo.industry.IndustryVo;

public class RegistrationTypeVo {

	private Long id;
	private String type;
	private Boolean status;
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
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public static RegistrationType getRegistrationType(RegistrationTypeVo registrationTypeVo) {
		RegistrationType registrationType = null;
		if(registrationTypeVo != null) {
			registrationType = new RegistrationType();
			registrationType.setId(registrationTypeVo.getId());
			registrationType.setType(registrationTypeVo.getType());
			registrationType.setStatus(registrationTypeVo.getStatus());
		}
		return registrationType;
	}
	
	public static RegistrationTypeVo getRegistrationTypeVo(RegistrationType registrationType) { 
		RegistrationTypeVo registrationTypeVo = null;
		if (registrationType != null) {
			registrationTypeVo = new RegistrationTypeVo();
			registrationTypeVo.setId(registrationType.getId());
			registrationTypeVo.setType(registrationType.getType());
			registrationTypeVo.setStatus(registrationType.getStatus());
		}
		return registrationTypeVo;
	}
}
