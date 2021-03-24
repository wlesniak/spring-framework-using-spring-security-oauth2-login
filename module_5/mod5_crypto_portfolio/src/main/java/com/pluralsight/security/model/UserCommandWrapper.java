package com.pluralsight.security.model;

import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class UserCommandWrapper<T> {

	private final OidcUser userDetails;
	private final T dto;

	public UserCommandWrapper(OidcUser userDetails, T dto) {
		this.userDetails = userDetails;
		this.dto = dto;
	}

	public String getUsername() {
		return this.userDetails.getSubject();
	}
	
	public OidcUser getUserDetails() {
		return this.userDetails;
	}

	public T getDto() {
		return this.dto;
	}

}
