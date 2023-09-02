package com.evantagesoft.entities.country;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="city")
public class City {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @ManyToOne()
    @JoinColumn(name = "state", referencedColumnName = "id")
    @JsonBackReference(value="states")
    private State state;
    
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

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	
}
