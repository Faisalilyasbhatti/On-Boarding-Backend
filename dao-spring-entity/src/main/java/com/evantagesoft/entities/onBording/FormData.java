package com.evantagesoft.entities.onBording;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;


@Entity
@Table(name="form_data")
public class FormData {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@LazyCollection(LazyCollectionOption.TRUE)
	@JoinColumn(name = "user", referencedColumnName = "userID")
	@JsonBackReference
	private UserOnBoarding user;
	private String name;
	private String value;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UserOnBoarding getUser() {
		return user;
	}
	public void setUser(UserOnBoarding user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
