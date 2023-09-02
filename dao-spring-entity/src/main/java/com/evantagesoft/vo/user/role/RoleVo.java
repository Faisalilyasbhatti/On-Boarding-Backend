package com.evantagesoft.vo.user.role;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.evantagesoft.entities.user.permission.Permission;
import com.evantagesoft.entities.user.role.Role;
import com.evantagesoft.vo.user.UserVo;
import com.evantagesoft.vo.user.permission.PermissionVo;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public class RoleVo {
	private static final Logger logger = LoggerFactory.getLogger(RoleVo.class);
	
	private Long id;
	private String code;
	private String name;
	private Set<PermissionVo> permissionVos;
	private RoleTypeVo roleTypeVo;
	private UserVo createdBy;
	private String createdDate;
	private UserVo updatedBy;
	private String updatedDate;
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
	public Set<PermissionVo> getPermissionVos() {
		return permissionVos;
	}
	public void setPermissionVos(Set<PermissionVo> permissionVos) {
		this.permissionVos = permissionVos;
	}
	public RoleTypeVo getRoleTypeVo() {
		return roleTypeVo;
	}
	public void setRoleTypeVo(RoleTypeVo roleTypeVo) {
		this.roleTypeVo = roleTypeVo;
	}
	public UserVo getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(UserVo createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {;
		this.createdDate = createdDate;
	}
	public UserVo getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(UserVo updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public static RoleVo getRoleVo(Role role) {
		RoleVo roleVo = null;
		if(role != null) {
			roleVo = new RoleVo();
			roleVo.setCode(role.getCode());
			roleVo.setName(role.getName());
			roleVo.setId(role.getId());
			roleVo.setCreatedDate(new SimpleDateFormat("dd-MM-yyyy").format(role.getCreatedDate()));
			roleVo.setUpdatedDate(StringUtils.isEmpty(role.getUpdatedDate()) ? null : new SimpleDateFormat("dd-MM-yyyy").format(role.getUpdatedDate()));
			roleVo.setStatus(role.getStatus());
			if(role.getPermissions() != null) {
				Set<PermissionVo> permissionVos = new HashSet<PermissionVo>();
				for(Permission permission : role.getPermissions()) {
					permissionVos.add(PermissionVo.getPermissionVo(permission));
				}
				roleVo.setPermissionVos(permissionVos);
			}
			if(role.getRoleType() != null) {
				roleVo.setRoleTypeVo(RoleTypeVo.getRoleVo(role.getRoleType()));
			}
		}
		return roleVo;
	}
	public static Role getRole(RoleVo roleVo) {
		Role role = null;
		if(roleVo != null) {
			role = new Role();
			role.setId(roleVo.getId());
			role.setCode(roleVo.getCode());
			role.setName(roleVo.getName());
			role.setCreatedBy(UserVo.getUser(roleVo.getCreatedBy()));
			role.setUpdatedBy(UserVo.getUser(roleVo.getUpdatedBy()));
			role.setStatus(roleVo.getStatus());
			if(!StringUtils.isEmpty(roleVo.getCreatedDate())) {
				try {
					role.setCreatedDate(new SimpleDateFormat("dd-MM-yyyy").parse(roleVo.getCreatedDate()));
				} catch (Exception e) {
					logger.error("",e);
				}
			}
			else{
				role.setCreatedDate(new Date());
			}

			if(roleVo.getRoleTypeVo() != null){
				role.setRoleType(RoleTypeVo.getRoleType(roleVo.getRoleTypeVo()));
			}
			if(roleVo.getPermissionVos() != null && roleVo.getPermissionVos().size() > 0){
				Set<Permission> permissions = new HashSet<>();
				for (PermissionVo permissionVo : roleVo.getPermissionVos()){
					permissions.add(PermissionVo.getPermission(permissionVo));
				}
				//roleVo.getPermissionVos().forEach(permissionVo -> permissions.add(PermissionVo.getPermission(permissionVo)));
				role.setPermissions(permissions);
			}
		}
		return role;
	}
}
