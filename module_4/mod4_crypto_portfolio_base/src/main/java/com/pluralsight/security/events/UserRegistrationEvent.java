package com.pluralsight.security.events;

import org.springframework.context.ApplicationEvent;

import com.pluralsight.security.entity.CryptoUser;

public class UserRegistrationEvent extends ApplicationEvent {

	private static final long serialVersionUID = -4113549487933175429L;
	private final CryptoUser user;
	
	public UserRegistrationEvent(CryptoUser user) {
		super(user);
		this.user=user;
	}

	public CryptoUser getUser() {
		return user;
	}
	
}
