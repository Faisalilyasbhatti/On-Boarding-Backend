package com.evantagesoft.service.customer;

import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.business.Business;
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
import com.evantagesoft.util.response.EVSResponse;
import com.evantagesoft.vo.account.AccountVo;
import com.evantagesoft.vo.customer.CustomerVo;
import com.evantagesoft.vo.response.Response;
import com.evantagesoft.vo.stakeHolder.StakeHolderVo;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private CustomerBusinessRepository customerBusinessRepository;
	
	
	
	@Autowired
	private CityRepository cityRepo;
	
	
	@Autowired
	private CountryRepository countryRepo;
	
	
	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private BusinessRepository businessRepository;

	public Response createBusinessCustomer(Map<String, Object> input) {
		Response response = new Response();
		Customer customer;
		try {
			CustomerVo customerVo = new org.codehaus.jackson.map.ObjectMapper().convertValue(input.get("customer"),
					CustomerVo.class);
			if (customerVo == null) {
				response.setResponse(EVSResponse.CUSTOMER_ERROR);
				return response;
			} else if (customerVo.getCustomerAddressVo() == null) {
				response.setResponse(EVSResponse.CUSTOMER_ADDRESS_ERROR);
				return response;
			} else {
				customer = CustomerVo.getCustomer(customerVo);
				Customer customerResp = this.save(customer);
				Business business = null;
				CustomerBusiness customerBusiness = null;
				if (customer.getAccount().getType().equals("operator")) {
					customerBusiness = new CustomerBusiness();
					Long businessId = customerVo.getBusinessId();
					business = businessRepository.findOne(businessId);
				}

				if (business != null) {
					customerRepository.save(customer);
					businessRepository.save(business);
					customerBusiness.setBusiness(business);
					customerBusiness.setCustomer(customer);
					customerBusinessRepository.save(customerBusiness);

					Account isExistAcc = accountRepository.findById(customer.getAccount().getId());
					isExistAcc.setStatus(3);
					accountRepository.save(isExistAcc);

				}
				if (customerVo.getType().equals("Operator")) {
					this.exportAsMerchant(customer, business.getFullName());
				}

				response.setResponse(EVSResponse.SUCCESS);
				response.setData("customer", customerResp);
			}
		} catch (Exception e) {
			logger.error("" + e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;

	}

	public Response createCustomer(Map<String, Object> input) {
		Response response = new Response();
		Customer customer;
		try {
			CustomerVo customerVo = new ObjectMapper().convertValue(input.get("customer"), CustomerVo.class);
			if (customerVo == null) {
				response.setResponse(EVSResponse.CUSTOMER_ERROR);
				return response;
			} else if (customerVo.getCustomerAddressVo() == null) {
				response.setResponse(EVSResponse.CUSTOMER_ADDRESS_ERROR);
				return response;
			} else if (customerVo.getCustomerDocumentVos() == null) {
				response.setResponse(EVSResponse.CUSTOMER_DOCUMENT);
				return response;
			} else if (customerVo.getAccountVo() == null) {
				response.setResponse(EVSResponse.CUSTOMER_ADDRESS_ERROR);
				return response;
			} else {
				Account account = accountRepository.findByEmail(customerVo.getAccountVo().getEmail());
				if (account != null) {
					customer = CustomerVo.getCustomer(customerVo);
					customer.setAccount(account);
					customer = customerRepository.save(customer);
					response.setResponse(EVSResponse.SUCCESS);
					response.setData("user", customer);
				}
			}
		} catch (Exception e) {
			logger.error("" + e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

	public Response searchCustomer(String email) {
		Response response = new Response();
//		List<StakeHolderVo> stakeholders;
		List<Object[]> stakeholders;
		try {
			if (StringUtils.isEmpty(email)) {
				response.setResponse(EVSResponse.INVALID_EMAIL);
			} else {
				Account account = accountRepository.findByEmail(email);
				if (account == null) {
					response.setResponse(EVSResponse.DATA_NOT_FOUND);
					return response;
				}
				Customer customer = customerRepository.findByAccount(account);
				if (customer == null) {
					response.setResponse(EVSResponse.UNCOMPLETE_CUSTOMER_DATA);
					return response;
				}
				
				
				City city = cityRepo.findById(Long.parseLong( customer.getCustomerAddresses().get(0).getCity()));
				CustomerVo customerResponse=CustomerVo.getCustomerVo(customer);
				
				Country country = countryRepo.findById(Long.parseLong(customer.getCustomerAddresses().get(0).getCountry()));
				
				
				State state =stateRepo.findById(Long.parseLong(customer.getCustomerAddresses().get(0).getState()));
				
				
				Long businessId=customerResponse.getCustomerBusiness().get(0).getId();
				 stakeholders= accountRepository.fetchStakeholderByBusinessId(businessId);
				 
				 if(stakeholders !=null) {
					 List<StakeHolderVo> stakeholderVo = new ArrayList<StakeHolderVo>();
					 for(Object[] item :stakeholders) {
						 StakeHolderVo stakeholder= new StakeHolderVo();
						 Object[] itemArr = (Object[]) item;
						 stakeholder.setId(Long.parseLong(itemArr[0].toString()));
						 stakeholder.setType((String) itemArr[1]);
						 stakeholder.setFullName((String) itemArr[2]);
						 stakeholder.setMobileNo((String) itemArr[3]);
						 stakeholder.setEmail((String) itemArr[4]);
						 stakeholder.setStatus((int) itemArr[6]);
						 stakeholder.setDesignation((String) itemArr[7]);
						 
						 
						 stakeholderVo.add(stakeholder);
					 }
					 customerResponse.getCustomerBusiness().get(0).setStakeHolder(stakeholderVo);					 
					 customerResponse.getCustomerAddressVo().setCity(city.getName());
					 customerResponse.getCustomerAddressVo().setCountry(country.getName());
					 customerResponse.getCustomerAddressVo().setState(state.getName());
					 customerResponse.getCustomerBusiness().get(0).getBusinessAddressVo().setCity(city.getName());
					 customerResponse.getCustomerBusiness().get(0).getBusinessAddressVo().setCountry(country.getName());
					 customerResponse.getCustomerBusiness().get(0).getBusinessAddressVo().setState(state.getName());
					 
				 }
				 
				response.setResponse(EVSResponse.SUCCESS);
				response.setData("customer",customerResponse);
			}
		} catch (Exception e) {
			response.setResponse(EVSResponse.SYSTEM_ERROR);
		}
		return response;
	}

	public Response searchCustomerByAccount(Long id) {
		Response response = new Response();
		Customer customer;
		try {
			if (id == null) {
				response.setResponse(EVSResponse.INVALID_ID);
			} else {
				Account account = accountRepository.findById(id);
				if (account == null) {
					response.setResponse(EVSResponse.DATA_NOT_FOUND);
					return response;
				}
				customer = customerRepository.findByAccount(account);
				if (customer == null) {
					response.setResponse(EVSResponse.DATA_NOT_FOUND);
					return response;
				}
				response.setResponse(EVSResponse.SUCCESS);
				response.setData("Customer", CustomerVo.getCustomerVo(customer));
			}

		} catch (Exception e) {
			e.printStackTrace();
			response.setResponse(EVSResponse.SYSTEM_ERROR);
		}
		return response;
	}

	public Customer save(Customer customer) {
		if (customer.getAccount() != null && !StringUtils.isEmpty(customer.getAccount().getEmail())) {
			Account account = accountRepository.findByEmail(customer.getAccount().getEmail());
			if (account == null) {
				account = accountRepository.save(customer.getAccount());
			}
			Customer customerTemp = customerRepository.findByAccount(account);
			if (customerTemp == null) {
				customer.setAccount(account);
				customer = customerRepository.save(customer);
			} else {
				if (!account.getType().equals("operator")) {
					customer = customerRepository.save(customer);

					Account isExistAcc = accountRepository.findById(customer.getAccount().getId());
					isExistAcc.setStatus(3);
					accountRepository.save(isExistAcc);
				} else {
					customer = customerTemp;
				}

			}
			return customer;
		} else {
			return null;
		}
	}

	public Response findAll() {
		Response response = new Response();
		try {

			List<Customer> customer = (List<Customer>) customerRepository.findAll();
			List<CustomerVo> customerVos = new ArrayList<CustomerVo>();

			for (Customer user : customer) {
				customerVos.add(CustomerVo.getCustomerVo(user));
			}
			response.setResponse(EVSResponse.SUCCESS);
			response.setData("customer", customerVos);
		} catch (Exception e) {
			logger.error("" + e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}
	
	public Response findByStatus(int status) {
		Response response = new Response();
		try {

			List<Customer> customer = (List<Customer>) customerRepository.findByStatus(status);
			List<CustomerVo> customerVos = new ArrayList<CustomerVo>();

			for (Customer user : customer) {
				customerVos.add(CustomerVo.getCustomerVo(user));
			}
			response.setResponse(EVSResponse.SUCCESS);
			response.setData("customer", customerVos);
		} catch (Exception e) {
			logger.error("" + e);
			response = new Response(EVSResponse.SYSTEM_ERROR, null);
		}
		return response;
	}

	public void exportAsMerchant(Customer customer, String businessName) {
		try {
			Map<String, String> request = new HashMap<>();
			request.put("firstName", customer.getFirstName());
			request.put("lastName", customer.getLastName());
			request.put("email", customer.getAccount().getEmail());
			request.put("fullName", businessName);
			request.put("masterID", "" + customer.getId());
			RestTemplate client = new RestTemplate();
			client.postForEntity("http://localhost:8080/dao-spring-api/merchant/register", request, Map.class);
		} catch (Exception e) {
			logger.error("" + e);
		}
	}

	public Response updateStatus(Map<String, String> input) {
		Response response = new Response(EVSResponse.SUCCESS, null);
		try {
			if (StringUtils.isEmpty(input.get("email"))) {
				response.setResponse(EVSResponse.EMAIL_IS_EMPTY);
			} else if (StringUtils.isEmpty(input.get("status"))) {
				response.setResponse(EVSResponse.INVALID_STATUS);
			} else if (StringUtils.isEmpty(input.get("businessID"))) {
				response.setResponse(EVSResponse.INVALID_BUSINESSID);
			} else {
				Account account = accountRepository.findByEmail(input.get("email"));
				int status;
				try {
					status = Integer.parseInt(input.get("status"));
				} catch (Exception e) {
					response.setResponse(EVSResponse.INVALID_STATUS);
					return response;
				}
				if (account == null) {
					response.setResponse(EVSResponse.INVALID_EMAIL);
				} else {
					Customer customer = customerRepository.findByAccount(account);
					Long businessID = Long.parseLong(input.get("businessID"));
					if (customer == null) {
						response.setResponse(EVSResponse.CUSTOMER_NOT_FOUND);
					} else {
						customer.setStatus(status);
						customerRepository.save(customer);
						if (status == 1) {
							// export data
							Business business = null;
							if (customer.getBusinesses() != null) {
								for (CustomerBusiness customerBusiness : customer.getBusinesses()) {
									if (customerBusiness.getBusiness() != null
											&& customerBusiness.getBusiness().getId().equals(businessID))
										business = customerBusiness.getBusiness();
									break;
								}
							}
							exportAsMerchant(customer, business != null ? business.getFullName() : null);
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("" + e);
			response.setResponse(EVSResponse.SYSTEM_ERROR);
		}
		return response;
	}
}
