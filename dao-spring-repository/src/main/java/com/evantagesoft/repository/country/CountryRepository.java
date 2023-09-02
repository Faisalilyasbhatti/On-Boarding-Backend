package com.evantagesoft.repository.country;

import org.springframework.data.repository.CrudRepository;
import com.evantagesoft.entities.country.Country;

public interface CountryRepository extends CrudRepository<Country,Long>{

	Country findById(Long id);
}
