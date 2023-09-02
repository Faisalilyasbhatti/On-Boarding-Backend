package com.evantagesoft.repository.business;

import com.evantagesoft.entities.business.CustomerBusiness;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Nand Khatri
 * @version 1.0
 * @date 1/19/2021
 */
public interface CustomerBusinessRepository extends CrudRepository<CustomerBusiness, Long> {
}
