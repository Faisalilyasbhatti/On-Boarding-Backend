package com.evantagesoft.vo.customer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.BeanUtils;

import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.business.CustomerBusiness;
import com.evantagesoft.entities.customer.Customer;
import com.evantagesoft.entities.customer.CustomerAddress;
import com.evantagesoft.entities.customer.CustomerDocument;
import com.evantagesoft.vo.account.AccountVo;
import com.evantagesoft.vo.business.BusinessVo;

public class CustomerVo {

    private Long id;
    private Long businessId;
	private String firstName;
    private String lastName;
    private String nationality;
    @JsonProperty("account")
    private AccountVo accountVo;
    private String gender;
    private int status;
    private String dateOfBirth;
    private String type;
    private String createdDate;
    private String updatedDate;
    @JsonProperty("documents")
    private List<CustomerDocumentVo> customerDocumentVos;
    @JsonProperty("address")
    private CustomerAddressVo customerAddressVo;
    
    private List<BusinessVo> customerBusiness;
    
    
 
	public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public AccountVo getAccountVo() {
        return accountVo;
    }
    public void setAccountVo(AccountVo accountVo) {
        this.accountVo = accountVo;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    public List<CustomerDocumentVo> getCustomerDocumentVos() {
        return customerDocumentVos;
    }
    
    public void setCustomerDocumentVos(List<CustomerDocumentVo> customerDocumentVos) {
        this.customerDocumentVos = customerDocumentVos;
    }
    public CustomerAddressVo getCustomerAddressVo() {
        return customerAddressVo;
    }
    public void setCustomerAddressVo(CustomerAddressVo customerAddressVo) {
        this.customerAddressVo = customerAddressVo;
    }
    
	public List<BusinessVo> getCustomerBusiness() {
		return customerBusiness;
	}
	public void setCustomerBusiness(List<BusinessVo> customerBusiness) {
		this.customerBusiness = customerBusiness;
	}
    public Long getBusinessId() {
		return businessId;
	}
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}


    public static Customer getCustomer(CustomerVo customerVo) throws Exception{
        Customer customer = null;
            if (customerVo != null) {
                customer= new Customer();
                if (customerVo.getAccountVo() != null) {
                    Account account = new Account();
                    BeanUtils.copyProperties(customerVo.getAccountVo(), account);
                    customer.setAccount(account);
                }
                customer.setDateOfBirth(customerVo.getDateOfBirth() != null
                        ? new SimpleDateFormat("yyyy-MM-dd").parse(customerVo.getDateOfBirth()):null);
                customer.setId(customerVo.getId());
                customer.setStatus(customerVo.getStatus());
                customer.setFirstName(customerVo.getFirstName());
                customer.setLastName(customerVo.getLastName());
                customer.setGender(customerVo.getGender());
                customer.setNationality(customerVo.getNationality());
                if (customerVo.getCustomerAddressVo() != null) {
                    CustomerAddress address = CustomerAddressVo.getCustomerAddress(customerVo.getCustomerAddressVo());
                    address.setCustomer(customer);
                    customer.setCustomerAddresses(new ArrayList<CustomerAddress>() {
                        {add(address);};
                    });
                }
//                if (customerVo.getCustomerDocumentVos() != null) {
//                    List<CustomerDocument> documents = new ArrayList<>();
//                    for (CustomerDocumentVo documentVo : customerVo.getCustomerDocumentVos()) {
//                        CustomerDocument document = CustomerDocumentVo.getCustomerDoc(documentVo);
//                        document.setCustomer(customer);
//                        documents.add(document);
//                    }
//                    customer.setCustomerDocuments(documents);
//                }
                
            }
            return customer;
    }

	
    public static CustomerVo getCustomerVo(Customer customer) throws Exception {
        CustomerVo customerVo = null;
        if (customer != null) {
            customerVo = new CustomerVo();
            customerVo.setId(customer.getId());
            customerVo.setAccountVo(AccountVo.getAccountVo(customer.getAccount()));
            customerVo.setFirstName(customer.getFirstName());
            customerVo.setLastName(customer.getLastName());
            customerVo.setDateOfBirth(customer.getDateOfBirth() != null
                    ? new SimpleDateFormat("yyyy-MM-dd").format(customer.getDateOfBirth()) : "");
            customerVo.setGender(customer.getGender());
            customerVo.setNationality(customer.getNationality());
            customerVo.setStatus(customer.getStatus());
            customerVo.setCreatedDate(customer.getCreatedDate() != null ?
                    new SimpleDateFormat("yyyy-MM-dd").format(customer.getCreatedDate()):"");
            customerVo.setUpdatedDate(customer.getUpdatedDate() != null ?
                    new SimpleDateFormat("yyyy-MM-dd").format(customer.getUpdatedDate()) :"");
            if (customer.getCustomerAddresses() != null && customer.getCustomerAddresses().size() > 0) {
                customerVo.setCustomerAddressVo(CustomerAddressVo.getCustomerAddressVo(customer.getCustomerAddresses().get(0)));
            }
            if (customer.getCustomerDocuments() != null) {
                List<CustomerDocumentVo> documentVos = new ArrayList<>();
                for (CustomerDocument document : customer.getCustomerDocuments()) {
                    documentVos.add(CustomerDocumentVo.getCustomerDocVo(document));
                }
                customerVo.setCustomerDocumentVos(documentVos);
            }
            
            if(customer.getBusinesses() !=null) {
                List<BusinessVo> businessVos = new ArrayList<>(); 
                for (CustomerBusiness business : customer.getBusinesses()) {
                	BusinessVo getBusiness=BusinessVo.getBusinessVo(business.getBusiness());
                	businessVos.add(getBusiness);
                }
                customerVo.setCustomerBusiness(businessVos);
            }
            
            
        }
        return customerVo;
    }

    
    
}
