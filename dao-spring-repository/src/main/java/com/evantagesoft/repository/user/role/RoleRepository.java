package com.evantagesoft.repository.user.role;

import org.springframework.data.repository.CrudRepository;

import com.evantagesoft.entities.user.role.Role;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public interface RoleRepository extends CrudRepository<Role, Long>{
    Role findByCode(String code);
}
