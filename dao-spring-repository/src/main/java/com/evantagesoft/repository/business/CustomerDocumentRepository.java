package com.evantagesoft.repository.business;

import org.springframework.data.repository.CrudRepository;

import com.evantagesoft.entities.business.BusinessDocument;
import com.evantagesoft.entities.customer.CustomerDocument;
import com.evantagesoft.vo.customer.CustomerDocumentVo;

public interface CustomerDocumentRepository extends CrudRepository<CustomerDocument,Long>{
	

}
