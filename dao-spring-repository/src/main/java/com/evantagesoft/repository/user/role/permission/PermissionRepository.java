package com.evantagesoft.repository.user.role.permission;

import org.springframework.data.repository.CrudRepository;

import com.evantagesoft.entities.user.permission.Permission;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public interface PermissionRepository extends CrudRepository<Permission, Long>{

}
