package com.evantagesoft.vo.country;

import java.util.ArrayList;
import java.util.List;

import com.evantagesoft.entities.country.Country;
import com.evantagesoft.entities.country.State;


public class CountryVo {

	private Long id;
    private String name;
    private int isActive;
    private List<StateVo> stateVos;
    
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
	public List<StateVo> getStateVos() {
		return stateVos;
	}
	public void setStateVos(List<StateVo> stateVos) {
		this.stateVos = stateVos;
	}

	  public static CountryVo getCountryVo(Country country) throws Exception {
		  CountryVo countryVo= null;
		  if (country != null) {
			  countryVo= new CountryVo();
			  countryVo.setId(country.getId());
			  countryVo.setIsActive(country.getIsActive());
			  countryVo.setName(country.getName());
			  if(country.getStates() != null) {
				  List<StateVo> stateVoList = new ArrayList<>();
				  for (State state : country.getStates()) {
					  stateVoList.add(StateVo.getStateVo(state));
	                }
				  countryVo.setStateVos(stateVoList);
			  }
		
		  }
		  
		  return countryVo;
	  }
	
}
