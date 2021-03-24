package com.pluralsight.security.util;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtil {
	
	public static String getUsername() {		
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
