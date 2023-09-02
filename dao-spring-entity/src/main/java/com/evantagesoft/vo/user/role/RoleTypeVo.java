package com.evantagesoft.vo.user.role;

import com.evantagesoft.entities.user.role.RoleType;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public class RoleTypeVo {

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
	
	public static RoleTypeVo getRoleVo(RoleType roleType ) {
		RoleTypeVo roleTypeVo = null;
		if(roleType != null) {
			roleTypeVo = new RoleTypeVo();
			roleTypeVo.setCode(roleType.getCode());
			roleTypeVo.setName(roleType.getName());
			roleTypeVo.setId(roleType.getId());
		}
		return roleTypeVo;
	}

	public static RoleType getRoleType(RoleTypeVo roleTypeVo) {
		RoleType roleType = null;
		if(roleTypeVo != null) {
			roleType = new RoleType();
			roleType.setCode(roleTypeVo.getCode());
			roleType.setName(roleTypeVo.getName());
			roleType.setId(roleTypeVo.getId());
		}
		return roleType;
	}
}
