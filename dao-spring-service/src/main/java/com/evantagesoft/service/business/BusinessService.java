package com.evantagesoft.service.business;
import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.business.Business;
import com.evantagesoft.entities.business.BusinessDocument;
import com.evantagesoft.entities.business.CustomerBusiness;
import com.evantagesoft.entities.country.City;
import com.evantagesoft.entities.country.Country;
import com.evantagesoft.entities.country.State;
import com.evantagesoft.entities.customer.Customer;
import com.evantagesoft.repository.State.StateRepository;
import com.evantagesoft.repository.account.AccountRepository;
import com.evantagesoft.repository.business.BusinessRepository;
import com.evantagesoft.repository.business.CustomerBusinessRepository;
import com.evantagesoft.repository.city.CityRepository;
import com.evantagesoft.repository.country.CountryRepository;
import com.evantagesoft.repository.customer.CustomerRepository;
import com.evantagesoft.util.cms.ContentRepositoryProcessor;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.account.AccountVo;
import com.evantagesoft.vo.business.BusinessVo;
import com.evantagesoft.vo.response.Response;
import com.evantagesoft.vo.stakeHolder.StakeHolderVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;


@Service
public class BusinessService {

    private static final Logger logger = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    private BusinessRepository businessRepository;
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerBusinessRepository customerBusinessRepository;
    
    @Autowired
	private CityRepository cityRepo;
	
	
	@Autowired
	private CountryRepository countryRepo;
	
	
	@Autowired
	private StateRepository stateRepo;

    //    @Autowired
//    private CustomerService customerService;

    public Response createBusiness(Map<String, Object> input) {
    	Response response = new Response();
    	Business business;
    	
    	try {
    		BusinessVo businessVo=new ObjectMapper().convertValue(input.get("business"), BusinessVo.class);
    	
    		 if (businessVo == null) {
               response.setResponse(EVSResponse.BUSINESS_ERROR);
               return response;
           }
    		 else if(businessVo.getBusinessAddressVo() == null) {
               response.setResponse(EVSResponse.BUSINESS_ADDRESS_ERROR);
               return response;
           }
    		  else if(businessVo.getBusinessDocumentVos() == null) {
                response.setResponse(EVSResponse.BUSINESS_DOCUMENT);
                return response;
            }
    		 
    		  else {
    			
    			  business = BusinessVo.getBusiness(businessVo);
    			  business = businessRepository.save(business);
    			  
    				 
    	            if (businessVo.getStakeHolder() != null) {
    	            	Account usersAccount;
    	            
    	            	
    	            	 for (StakeHolderVo stackholder : businessVo.getStakeHolder()) {
    	            		 if(!StringUtils.isEmpty(stackholder.getEmail())) {
    	            			 Account acc=AccountVo.getAccountFromStackHolder(stackholder);
    	            			 usersAccount= accountRepository.save(acc);
    	            			 
    	     	            	 Customer customer= new Customer();
    	     	            	CustomerBusiness customerBusiness= new CustomerBusiness();
    	     	            	Customer newCustomer;
    	     	            	
    	     	            	
    	     	           
    	     	            	
    	     	            	 customer.setAccount(usersAccount);
    	     	            	 customer.setFirstName(stackholder.getFirstName());
    	            			 customer.setLastName(stackholder.getLastName());
    	            			 customer.setGender(stackholder.getGender());
    	            			 customer.setDesignation(stackholder.getDesignation());
    	            			 newCustomer= customerRepository.save(customer);
    	            			 
    	            			 customerBusiness.setBusiness(business);
    	            			 customerBusiness.setCustomer(newCustomer);
    	            			 customerBusinessRepository.save(customerBusiness);
    	            			 
    	            		 }
    	                 }
    	                
    	            }
    			  
    			  
    			   Account isExistAcc= accountRepository.findById(business.getAccount().getId());
    			   isExistAcc.setStatus(2);
    			   accountRepository.save(isExistAcc);
    			  if (business.getBusinessDocuments() != null) {
                    ContentRepositoryProcessor repo = new ContentRepositoryProcessor();
                    for (BusinessDocument document : business.getBusinessDocuments()) {
                        if (document.getDocument() != null) {
                            repo.moveNode(business.getId().toString(), document.getDocument());
                        }
                    }
                }
    			 
    			  
    			  response.setResponse(EVSResponse.SUCCESS);
                response.setData("user",business);  
    		  }
    		 
    		 
    	}
    	catch (Exception e) {
          logger.error(""+e);
          e.printStackTrace();
          response = new Response(EVSResponse.SYSTEM_ERROR, null);
      }
    	return response;
    }
    
    
	public Response searchBusiness(Long id) {
		Response response = new Response();
		Business business;
		try {
			if (id==null) {
				response.setResponse(EVSResponse.INVALID_ID);
			}else {
				Account account = accountRepository.findById(id);
				if (account == null) {
					response.setResponse(EVSResponse.DATA_NOT_FOUND);
					return response;
				}
				
				business = businessRepository.findByAccount(account);
				if (business == null) {
					response.setResponse(EVSResponse.DATA_NOT_FOUND);
					return response;
				}
				
				response.setResponse(EVSResponse.SUCCESS);
				response.setData("Business",BusinessVo.getBusinessVo(business));
			}
			
			
	} catch (Exception e) {
		e.printStackTrace();
		response.setResponse(EVSResponse.SYSTEM_ERROR);
	}
	return response;
	}
    
//    public Response createBusiness(Map<String, Object> input) {
//        Response response = new Response();
//        Business business;
//        try {
//            BusinessVo businessVo=new ObjectMapper().convertValue(input.get("business"), BusinessVo.class);
//            if (businessVo == null) {
//                response.setResponse(EVSResponse.BUSINESS_ERROR);
//                return response;
//            }
//            else if(businessVo.getBusinessAddressVo() == null) {
//                response.setResponse(EVSResponse.BUSINESS_ADDRESS_ERROR);
//                return response;
//            }
//            else if(businessVo.getBusinessDocumentVos() == null) {
//                response.setResponse(EVSResponse.BUSINESS_DOCUMENT);
//                return response;
//            }
//            else if(businessVo.getCustomerVos() == null) {
//                response.setResponse(EVSResponse.CUSTOMER_ERROR);
//                return response;
//            }
////            else if(businessVo.getAccount() == null) {
////                response.setResponse(EVSResponse.CUSTOMER_ADDRESS_ERROR);
////                return response;
////            }
//            else {
//                business = BusinessVo.getBusiness(businessVo);
//                List<CustomerBusiness> customerBusinessList = business.getCustomers();
//                business = businessRepository.save(business);
//                if (business.getBusinessDocuments() != null) {
//                    ContentRepositoryProcessor repo = new ContentRepositoryProcessor();
//                    for (BusinessDocument document : business.getBusinessDocuments()) {
//                        if (document.getDocument() != null) {
//                            repo.moveNode(business.getFullName(), document.getDocument());
//                        }
//                    }
//                }
//                for (CustomerBusiness customerBusiness : customerBusinessList) {
//                    Customer customer = customerService.save(customerBusiness.getCustomer());
//                    if (customer == null) {
//                        continue;
//                    }
//                    customerBusiness.setBusiness(business);
//                    customerBusiness.setCustomer(customer);
//                    customerBusinessRepository.save(customerBusiness);
////                    if (customerBusiness.getType().equals("Operator")) {
////                        customerService.exportAsMerchant(customer, business.getFullName());
////                    }
//                }
//                response.setResponse(EVSResponse.SUCCESS);
//                response.setData("user",business);
//            }
//
//
//        } catch (Exception e) {
//            logger.error(""+e);
//            e.printStackTrace();
//            response = new Response(EVSResponse.SYSTEM_ERROR, null);
//        }
//        return response;
//
//    }

}
