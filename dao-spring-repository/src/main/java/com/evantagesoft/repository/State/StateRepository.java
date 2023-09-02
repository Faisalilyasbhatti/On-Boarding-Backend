package com.evantagesoft.repository.State;

import org.springframework.data.repository.CrudRepository;

import com.evantagesoft.entities.country.State;

public interface StateRepository extends CrudRepository<State, Long> {

	
	State findById(Long id);
}
