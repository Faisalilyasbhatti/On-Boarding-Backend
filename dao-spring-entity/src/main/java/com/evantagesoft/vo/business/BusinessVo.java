package com.evantagesoft.vo.business;
import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.business.Business;
import org.springframework.beans.BeanUtils;
import com.evantagesoft.entities.business.BusinessAddress;
import com.evantagesoft.entities.business.BusinessDocument;
import com.evantagesoft.entities.business.CustomerBusiness;
import com.evantagesoft.vo.account.AccountVo;
import com.evantagesoft.vo.customer.CustomerVo;
import com.evantagesoft.vo.stakeHolder.StakeHolderVo;
import com.fasterxml.jackson.annotation.JsonGetter;
import java.util.ArrayList;
import java.util.List;

public class BusinessVo {

    private Long id;
    private String fullName;
    private String registrationNo;
    private String taxNo;
    private String website;
    private int status;
	private String industry;
	private String registrationType;
    private String createdDate;
    private String updatedDate;
    private List<BusinessDocumentVo> businessDocumentVos;
    private BusinessAddressVo businessAddressVo;
    private List<CustomerBusinessVo> customerBusinessVos;
    private List<CustomerVo> customerVos;
    private AccountVo accountVo;
    private List<StakeHolderVo> stakeHolder;

	@JsonGetter("account")
    public AccountVo getAccountVo() {
		return accountVo;
	}
	public void setAccountVo(AccountVo accountVo) {
		this.accountVo = accountVo;
	}

	@JsonGetter("stakeHolder")
    public List<StakeHolderVo> getStakeHolder() {
		return stakeHolder;
	}
	public void setStakeHolder(List<StakeHolderVo> stakeHolder) {
		this.stakeHolder = stakeHolder;
	}
	
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getRegistrationNo() {
        return registrationNo;
    }
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }
    public String getTaxNo() {
        return taxNo;
    }
    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }
    public String getWebsite() {
        return website;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public String getUpdatedDate() {
        return updatedDate;
    }
    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    @JsonGetter("customer")
    public List<CustomerVo> getCustomerVos() {
        return customerVos;
    }
    public void setCustomerVos(List<CustomerVo> customerVos) {
        this.customerVos = customerVos;
    }

    @JsonGetter("documents")
    public List<BusinessDocumentVo> getBusinessDocumentVos() {
        return businessDocumentVos;
    }
    public void setBusinessDocumentVos(List<BusinessDocumentVo> businessDocumentVos) {
        this.businessDocumentVos = businessDocumentVos;
    }

    @JsonGetter("address")
    public BusinessAddressVo getBusinessAddressVo() {
        return businessAddressVo;
    }
    public void setBusinessAddressVo(BusinessAddressVo businessAddressVo) {
        this.businessAddressVo = businessAddressVo;
    }
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getRegistrationType() {
		return registrationType;
	}
	public void setRegistrationType(String registrationType) {
		this.registrationType = registrationType;
	}
	
    public static Business getBusiness(BusinessVo businessVo) throws Exception{
        Business business = null;
        if (businessVo != null) {
            business= new Business();
            business.setId(businessVo.getId());
            business.setStatus(businessVo.getStatus());
            business.setFullName(businessVo.getFullName());
            business.setRegistrationNo(businessVo.getRegistrationNo());
            business.setTaxNo(businessVo.getTaxNo());
            business.setIndustry(businessVo.getIndustry());
            business.setRegistrationType(businessVo.getRegistrationType());
            business.setWebsite(businessVo.getWebsite());

            if (businessVo.getAccountVo() != null) {
                Account account = new Account();
                BeanUtils.copyProperties(businessVo.getAccountVo(), account);
                business.setAccount(account);
            }
            if (businessVo.getBusinessAddressVo() != null) {
                BusinessAddress address = BusinessAddressVo.getBusinessAddress(businessVo.getBusinessAddressVo());
                address.setBusiness(business);
                business.setBusinessAddresses(new ArrayList<BusinessAddress>() {
                    {add(address);};
                });
            }
            if (businessVo.getBusinessDocumentVos() != null) {
                List<BusinessDocument> documents = new ArrayList<>();
                for (BusinessDocumentVo documentVo : businessVo.getBusinessDocumentVos()) {
                    BusinessDocument document = BusinessDocumentVo.getBusinessDocumnet(documentVo);
                    document.setBusiness(business);
                    documents.add(document);
                }
                business.setBusinessDocuments(documents);
            }
            if (businessVo.getCustomerVos() != null) {
                List<CustomerBusiness> customers = new ArrayList<>();
                for (CustomerVo customerVo : businessVo.getCustomerVos()) {
                    CustomerBusiness customerBusiness = new CustomerBusiness();
                    customerBusiness.setCustomer(CustomerVo.getCustomer(customerVo));
                    customerBusiness.setBusiness(business);
                    customerBusiness.setType(customerVo.getType());
                    customers.add(customerBusiness);
                }
                business.setCustomers(customers);
            }
        }
        return business;
    }

    
    public static BusinessVo getBusinessVo(Business business) throws Exception{
    	BusinessVo businessVo = null;
   
    	  if (business != null) {
    		  businessVo= new BusinessVo();
    		  businessVo.setId(business.getId());
    		  businessVo.setStatus(business.getStatus());
    		  businessVo.setFullName(business.getFullName());
    		  businessVo.setRegistrationNo(business.getRegistrationNo());
    		  businessVo.setTaxNo(business.getTaxNo());
    		  businessVo.setWebsite(business.getWebsite());
    		  
    	  }
    	  if (business.getBusinessAddresses() != null) {
    		  for (BusinessAddress obj : business.getBusinessAddresses()) {
                  BusinessAddressVo businessAddressVo = BusinessAddressVo.getBusinessAddressVo(obj);
                  businessVo.setBusinessAddressVo(businessAddressVo);
    		  }
    		  
          }
    	  if (business.getBusinessDocuments() != null) {
              List<BusinessDocumentVo> documents = new ArrayList<>();
              for (BusinessDocument documentVo : business.getBusinessDocuments()) {
            	  BusinessDocumentVo document = BusinessDocumentVo.getBusinessDocumnetVo(documentVo);
                  documents.add(document);
              }
              businessVo.setBusinessDocumentVos(documents);
          }
    	  
    	  
    	  
    	  return businessVo;
    }
    	
}
