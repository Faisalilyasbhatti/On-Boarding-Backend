package com.evantagesoft.service.customer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.business.BusinessDocument;
import com.evantagesoft.entities.customer.Customer;
import com.evantagesoft.entities.customer.CustomerDocument;
import com.evantagesoft.repository.account.AccountRepository;
import com.evantagesoft.repository.business.CustomerDocumentRepository;
import com.evantagesoft.util.cms.ContentRepositoryProcessor;
import com.evantagesoft.util.email.EmailUtil;
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.customer.CustomerDocumentVo;
import com.evantagesoft.vo.customer.CustomerVo;
import com.evantagesoft.vo.response.Response;

@Service
public class CustomerDocumentService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerDocumentService.class);
    
	@Autowired
	private	CustomerDocumentRepository customerDocumentRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	 @Autowired
	    private EmailUtil emailUtil;
	
	public Response	uploadCustomerDocument(Map<String, Object> input) {
		Response response = new Response();
		 Customer customer = new Customer();
		 List<CustomerDocumentVo> customerDocumentVos;
		try {
			CustomerVo customerVo = new ObjectMapper().convertValue(input.get("customer"), CustomerVo.class);
//			Map<String, Object> items = ((List<Map<String, Object>>) input.get("customer")).get(0);
			 			
			if (customerVo == null) {
				response.setResponse(EVSResponse.CUSTOMER_ERROR);
				return response;
			}
			
			else {
				customer.setId(customerVo.getId());
				customer.setFirstName(customerVo.getFirstName());
				 if (customerVo.getCustomerDocumentVos() != null) {
                   List<CustomerDocument> documents = new ArrayList<>();
                   ContentRepositoryProcessor repo = new ContentRepositoryProcessor();
                   for (CustomerDocumentVo documentVo : customerVo.getCustomerDocumentVos()) {
                       CustomerDocument document = CustomerDocumentVo.getCustomerDoc(documentVo);
                       document.setCustomer(customer);
                       document = this.customerDocumentRepository.save(document);
                       
                       repo.moveNode(customer.getId().toString(), document.getDocument());   
                   }
               }
               Account isExistAcc= accountRepository.findById(customerVo.getAccountVo().getId());
               isExistAcc.setStatus(4);
    		   accountRepository.save(isExistAcc);
				 
				 emailUtil.sendWellcomeEmail(customerVo.getAccountVo().getEmail(), "DAO Registration Completed", customer.getFirstName());
//				 emailUtil.sendOtpEmail(otpVo.getAccountVo().getEmail(), "DAO onboarding OTP", otp.getEmailOtp(), otpVo.getAccountVo().getFullName());
			     response.setResponse(EVSResponse.SUCCESS);
			        response.setData("customer","Done");
				
			}
			
			
		}
		catch (Exception e) {
			logger.error("" + e);
			e.printStackTrace();
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}
	
}
