package com.example.demo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

	@GetMapping("/token")
	public String getAccessToken() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		DefaultOidcUser principal = (DefaultOidcUser)auth.getPrincipal();
		return principal.getIdToken().getTokenValue();
	}
	
}
