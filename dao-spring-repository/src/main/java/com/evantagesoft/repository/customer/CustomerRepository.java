package com.evantagesoft.repository.customer;
import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.customer.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface CustomerRepository extends CrudRepository<Customer,Long> {

    public Customer findByAccount(Account account);
    
    @Query(value = "SELECT * FROM customer c INNER JOIN account a ON c.account=a.id AND a.type='operator' AND c.status= :status",nativeQuery = true)
    List<Customer> findByStatus(@Param("status") int  status);
}
