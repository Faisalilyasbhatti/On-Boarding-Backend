package com.evantagesoft.entities.user.role;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.evantagesoft.entities.user.User;
import com.evantagesoft.entities.user.permission.Permission;


/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique = true)
	private String code;
	private String name;
	@ManyToMany(cascade=CascadeType.DETACH)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="role_permissions", 
					joinColumns= {@JoinColumn(name="role", referencedColumnName="id")},
					inverseJoinColumns= {@JoinColumn(name="permission", referencedColumnName="id")})
	private Set<Permission> permissions;
	@ManyToOne
	@JoinColumn(name="roleType")
	private RoleType roleType;
	@ManyToOne
	@JoinColumn(name="createdBy")
	private User createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@ManyToOne
	@JoinColumn(name="updatedBy")
	private User updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	@ManyToMany(mappedBy="roles", cascade=CascadeType.DETACH)
	private List<User> users;
	private int status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
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
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
