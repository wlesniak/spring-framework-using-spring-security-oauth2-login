package com.pluralsight.security.model;

import com.pluralsight.security.authn.CryptoPrincipal;

public class UserCommandWrapper<T> {

	private final CryptoPrincipal userDetails;
	private final T dto;

	public UserCommandWrapper(CryptoPrincipal userDetails, T dto) {
		this.userDetails = userDetails;
		this.dto = dto;
	}

	public String getUsername() {
		return this.userDetails.getUsername();
	}
	
	public CryptoPrincipal getUserDetails() {
		return this.userDetails;
	}

	public T getDto() {
		return this.dto;
	}

}
