package com.evantagesoft.repository.user;
import com.evantagesoft.entities.onBording.Authentication;
import com.evantagesoft.vo.auth.AuthenticationVo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AuthenticationRepository extends CrudRepository<Authentication,Long> {

    public Authentication save(Authentication authentication);

    @Query(value="  SELECT * from otp where mobileOtp=:otp and account=:userId and id=( SELECT MAX(id) FROM otp )", nativeQuery = true)
    public Authentication findByOtpAndUserId(@Param(value = "otp")String otp, @Param(value = "userId")int userId);
}
