package com.evantagesoft.entities.country;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="country")
public class Country {

	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "isActive")
    private int isActive;

    @OneToMany(mappedBy = "country", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<State> states;
     
    public List<State> getStates() {
		return states;
	}

	public void setStates(List<State> states) {
		this.states = states;
	}

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
   
}
