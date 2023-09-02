package com.evantagesoft.repository.account;
import com.evantagesoft.entities.account.Account;
import com.evantagesoft.vo.stakeHolder.StakeHolderVo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends CrudRepository<Account,Long> {

   public Account findByEmail(String email);

   public Account findById(Long id);
   
   @Query(value="SELECT a.*,c.designation FROM customer_business cb\r\n"
   		+ "INNER JOIN customer c ON cb.customer=c.id\r\n"
   		+ "INNER JOIN business b ON cb.business=b.id and b.id=:businessId\r\n"
   		+ "INNER JOIN account a ON c.account=a.id\r\n"
   		+ "WHERE a.type<>'operator'", nativeQuery = true)
   public List<Object[]> fetchStakeholderByBusinessId(@Param("businessId") Long businessId);
}
