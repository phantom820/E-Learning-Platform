package com.api.platform.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class SecurityUtil {
	
	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public static String encodePassword(String rawPassword) {
		return encoder.encode(rawPassword);
	}
	
	public static boolean verifyPassword(String rawPassword,String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}

}
