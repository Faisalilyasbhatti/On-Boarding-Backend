package com.evantagesoft.repository.industry;

import org.springframework.data.repository.CrudRepository;

import com.evantagesoft.entities.industry.Industry;
import java.util.List;

public interface IndustryRepository extends CrudRepository<Industry, Long> {
	
	List<Industry> findAllByStatusIsTrue();
}
