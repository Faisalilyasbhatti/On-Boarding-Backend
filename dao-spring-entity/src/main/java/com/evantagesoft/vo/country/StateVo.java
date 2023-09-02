package com.evantagesoft.vo.country;


import java.util.ArrayList;
import java.util.List;

import com.evantagesoft.entities.country.City;
import com.evantagesoft.entities.country.State;

public class StateVo {

	
	private Long id;
	private String name;
	private Long country;
	private int isActive;
	private List<CityVo> cityVos;
	
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
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	public List<CityVo> getCityVos() {
		return cityVos;
	}
	public void setCityVos(List<CityVo> cityVos) {
		this.cityVos = cityVos;
	}
	
	public static StateVo getStateVo(State state) throws Exception {
		StateVo stateVo = null;
		if (state != null) {
			stateVo= new StateVo();
			stateVo.setId(state.getId());
			stateVo.setIsActive(state.getIsActive());
			stateVo.setName(state.getName());
			stateVo.setCountry(state.getCountry().getId());
			  if(state.getCities() != null) {
				  List<CityVo> cityVoList = new ArrayList<>();
				  for (City city : state.getCities()) {
					  cityVoList.add(CityVo.getCityVo(city));
	                }
				  stateVo.setCityVos(cityVoList);
			  }
		}

		return stateVo;
	}
}
