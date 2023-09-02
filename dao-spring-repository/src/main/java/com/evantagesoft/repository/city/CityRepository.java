package com.evantagesoft.repository.city;

import org.springframework.data.repository.CrudRepository;

import com.evantagesoft.entities.country.City;


public interface CityRepository extends CrudRepository<City,Long>{

	City findById(Long id);
}
