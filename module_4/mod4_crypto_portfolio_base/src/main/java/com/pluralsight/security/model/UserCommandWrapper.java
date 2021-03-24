package com.pluralsight.security.model;

import com.pluralsight.security.authn.CustomUserDetails;

public class UserCommandWrapper<T> {

	private final CustomUserDetails userDetails;
	private final T dto;

	public UserCommandWrapper(CustomUserDetails userDetails, T dto) {
		this.userDetails = userDetails;
		this.dto = dto;
	}

	public String getUsername() {
		return this.userDetails.getUsername();
	}
	
	public CustomUserDetails getUserDetails() {
		return this.userDetails;
	}

	public T getDto() {
		return this.dto;
	}

}
