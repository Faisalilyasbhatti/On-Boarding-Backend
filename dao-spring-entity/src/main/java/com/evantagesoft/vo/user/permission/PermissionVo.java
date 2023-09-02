package com.evantagesoft.vo.user.permission;

import com.evantagesoft.entities.user.permission.Permission;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public class PermissionVo {
	
	private Long id;
	private String code;
	private String name;
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
	
	public static PermissionVo getPermissionVo(Permission permission) {
		PermissionVo permissionVo = null;
		if(permission != null) {
			permissionVo = new PermissionVo();
			permissionVo.setCode(permission.getCode());
			permissionVo.setId(permission.getId());
			permissionVo.setName(permission.getName());
		}
		return permissionVo;
	}
	
	public static Permission getPermission(PermissionVo permissionVo) {
		Permission permission = null;
		if(permissionVo != null) {
			permission = new Permission();
			permission.setId(permissionVo.getId());
			permission.setCode(permissionVo.getCode());
			permission.setName(permissionVo.getCode());
		}
		return permission;
	}
}
