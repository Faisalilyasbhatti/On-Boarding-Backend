package com.evantagesoft.vo.country;

import org.springframework.beans.BeanUtils;

import com.evantagesoft.entities.country.City;

public class CityVo {

	private Long id;
	private String name;
	private String state;
	private int isActive;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public static CityVo getCityVo(City city) throws Exception {
		CityVo cityVo = null;
		if (city != null) {
			cityVo= new CityVo();
			BeanUtils.copyProperties(city, cityVo);
			cityVo.setState(city.getState() != null ? city.getState().getName() : "");
		}

		return cityVo;
	}
}
