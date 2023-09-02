package com.evantagesoft.repository.business;
import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.business.Business;
import org.springframework.data.repository.CrudRepository;

public interface BusinessRepository extends CrudRepository<Business,Long> {

	Business findByAccount(Account account);



}
