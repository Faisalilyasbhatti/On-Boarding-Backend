package com.evantagesoft.repository.user;
import com.evantagesoft.entities.onBording.UserOnBoarding;
import org.springframework.data.repository.CrudRepository;
import com.evantagesoft.entities.onBording.FormData;

public interface FormDataRepository extends CrudRepository<FormData,Long> {
	
	Iterable<FormData> findByUser(UserOnBoarding user);
}