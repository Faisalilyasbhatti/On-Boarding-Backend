package com.evantagesoft.vo.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.evantagesoft.entities.user.User;
import com.evantagesoft.entities.user.role.Role;
import com.evantagesoft.vo.user.role.RoleVo;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public class UserVo {

	private Long userID;
	private String firstName;
	private String email;
	private int status;
	private String password;
	private String lastName;
	private List<RoleVo> roleVos;
	private UserVo createdBy;
	private String createdDate;
	private UserVo updatedBy;
	private String updatedDate;
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
	public List<RoleVo> getRoleVos() {
		return roleVos;
	}
	public void setRoleVos(List<RoleVo> roleVos) {
		this.roleVos = roleVos;
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
	public void setCreatedDate(String createdDate) {
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
	public static UserVo getUserVo(User user, int level) {
		UserVo userVo = null;
		if(user != null) {
			userVo = new UserVo();
			userVo.setEmail(user.getEmail());
			userVo.setLastName(user.getLastName());
			userVo.setStatus(user.getStatus());
			userVo.setFirstName(user.getFirstName());
			userVo.setUserID(user.getUserID());
			userVo.setCreatedDate(user.getCreatedDate() != null ? new SimpleDateFormat("dd-MM-yyyy").format(user.getCreatedDate()): "");
			userVo.setUpdatedDate(user.getUpdatedDate() != null ? new SimpleDateFormat("dd-MM-yyyy").format(user.getCreatedDate()) : "" );
			if (level >= 1) {
				if(user.getRoles() != null) {
					List<RoleVo> roleVos = new ArrayList<RoleVo>();
					for(Role role : user.getRoles()) {
						roleVos.add(RoleVo.getRoleVo(role));
					}
					userVo.setRoleVos(roleVos);
				}
				if(user.getCreatedBy() != null) {
					UserVo createdByVo = new UserVo();
					createdByVo.setUserID(user.getCreatedBy().getUserID());
					createdByVo.setEmail(user.getCreatedBy().getEmail());
					createdByVo.setLastName(user.getCreatedBy().getLastName());
					userVo.setCreatedBy(createdByVo);
				}
				if(user.getUpdatedBy() != null) {
					UserVo updatedByVo = new UserVo();
					updatedByVo.setUserID(user.getUpdatedBy().getUserID());
					updatedByVo.setEmail(user.getUpdatedBy().getEmail());
					updatedByVo.setLastName(user.getUpdatedBy().getLastName());
					userVo.setUpdatedBy(updatedByVo);
				}
			}
		}
		return userVo;
	}

	// return user object of userVo
	public static User getUser(UserVo userVo) {
		User user = null;
		if(userVo != null) {
			user = new User();
			user.setUserID(userVo.getUserID());
			user.setEmail(userVo.getEmail());
			user.setLastName(userVo.getLastName());
			user.setPassword(userVo.getPassword());
			user.setFirstName(userVo.getFirstName());
			user.setStatus(userVo.getStatus());
			// TODO:
			if(userVo.getRoleVos() != null) {
				List<Role> roles = new ArrayList<Role>();
				for(RoleVo roleVo: userVo.getRoleVos()) {
					roles.add(RoleVo.getRole(roleVo));
				}
				user.setRoles(roles);
			}
			if(userVo.getCreatedBy() != null) {
				User createdBy = new User();
				createdBy.setUserID(userVo.getCreatedBy().getUserID());
				user.setCreatedBy(createdBy);
			}
			if(userVo.getUpdatedBy() != null) {
				User updatedBy = new User();
				updatedBy.setUserID(userVo.getUpdatedBy().getUserID());
			}
		}
		return user;
	}
}
