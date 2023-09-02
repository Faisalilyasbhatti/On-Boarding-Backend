package com.evantagesoft.repository.auth;

import com.evantagesoft.entities.account.Account;
import com.evantagesoft.entities.auth.Otp;
import com.evantagesoft.entities.onBording.Authentication;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OtpRepository extends CrudRepository<Otp,Long> {

    @Query(value="  SELECT * from otp where mobileOtp=:otp and account=:userId and id=( SELECT MAX(id) FROM otp )", nativeQuery = true)
    public Otp findByOtpAndUserId(@Param(value = "otp")String otp, @Param(value = "userId") Long userId);

    public Otp save(Otp otp);
}
