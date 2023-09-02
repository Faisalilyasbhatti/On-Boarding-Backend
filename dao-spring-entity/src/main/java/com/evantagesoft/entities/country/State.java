package com.evantagesoft.entities.country;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="state")
public class State {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @ManyToOne()
    @JoinColumn(name = "country", referencedColumnName = "id")
    @JsonBackReference(value="countries")
    private Country country;
    
    @OneToMany(mappedBy = "state")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<City> cities;
    
    public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}

	@Column(name = "isActive")
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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
}
