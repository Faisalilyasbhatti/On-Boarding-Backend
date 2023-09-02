package com.evantagesoft.vo.industry;

import java.util.Date;

import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.industry.Industry;
import com.evantagesoft.vo.account.AccountVo;

/**
 * @author Kamlesh
 *
 */
public class IndustryVo {

	
	private Long id;
	private String name;
	private Boolean status;
	private Date createdDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public static Industry getIndustry(IndustryVo industryVo) {
		Industry industry = null;
		if(industryVo != null) {
			industry = new Industry();
			industry.setId(industryVo.getId());
			industry.setName(industryVo.getName());
			industry.setStatus(industryVo.getStatus());
		}
		return industry;
	}
	
	public static IndustryVo getIndustryVo(Industry industry) { 
		IndustryVo industryVo = null;
		if (industry != null) {
			industryVo = new IndustryVo();
			industryVo.setId(industry.getId());
			industryVo.setName(industry.getName());
			industryVo.setStatus(industry.getStatus());
		}
		return industryVo;
	}
	
	
}
