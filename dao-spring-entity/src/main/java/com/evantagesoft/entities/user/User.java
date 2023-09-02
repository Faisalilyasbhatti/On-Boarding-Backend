package com.evantagesoft.entities.user;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.evantagesoft.entities.user.role.Role;


/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
@Entity
@Table(name="user")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userID;
	private String firstName;
	private String email;
	private int status;
	private String password;
	private String lastName;
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name="user_role",
				joinColumns= {@JoinColumn(name="user", nullable = false, updatable = false, referencedColumnName="userID")},
				inverseJoinColumns= {@JoinColumn(name="role", nullable = false, updatable = false, referencedColumnName="id")}
			  )
	private List<Role> roles;
	@ManyToOne
	@JoinColumn(name="createdBy")
	private User createdBy;
	@Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@ManyToOne
	@JoinColumn(name="updatedBy")
	private User updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	@OneToMany(mappedBy="createdBy", cascade=CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<User> childUsers;
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	@PrePersist
	public void setCreatedDate() {
		this.createdDate = new Date();
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public User getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	@PreUpdate
	public void setUpdatedDate() {
		this.updatedDate = new Date();
	}
	public List<User> getChildUsers() {
		return childUsers;
	}
	public void setChildUsers(List<User> childUsers) {
		this.childUsers = childUsers;
	}
}
