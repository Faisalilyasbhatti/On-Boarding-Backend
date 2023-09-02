package com.evantagesoft.repository.user;

import org.springframework.data.repository.CrudRepository;

import com.evantagesoft.entities.user.User;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public interface UserRepository extends CrudRepository<User, Long> {
	public User findByEmailAndPassword(String userEmail, String password);
	public User findByEmail(String email);
}
