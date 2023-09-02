package com.evantagesoft.util.encrypt;

import org.springframework.security.crypto.bcrypt.BCrypt;

/**
 * @author Nand Khatri
 * Nov 05th 2020
 */
public class BCryptPasswordGenerator {

	public static String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt(12));
	}
	
	public static boolean match(String password, String hashPass) {
		return BCrypt.checkpw(password, hashPass);
	}
}
