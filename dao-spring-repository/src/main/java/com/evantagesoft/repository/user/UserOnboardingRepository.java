package com.evantagesoft.repository.user;

import com.evantagesoft.entities.onBording.UserOnBoarding;
import com.evantagesoft.entities.onBording.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserOnboardingRepository extends CrudRepository<Users,Long> {

    @Override
    Iterable<Users> findAll();

    public Users findByEmailAddress(@Param(value = "email")String email);

    public Users save(Users user);

    public Users  findById(long id);
}
