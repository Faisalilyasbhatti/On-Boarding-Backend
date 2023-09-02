package com.evantagesoft.repository.registrationType;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.evantagesoft.entities.registrationType.RegistrationType;

public interface RegistrationTypeRepository extends CrudRepository<RegistrationType, Long> {
	
	List<RegistrationType> findAllByStatusIsTrue();

}
