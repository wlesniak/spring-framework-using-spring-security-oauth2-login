package com.pluralsight.security.service;

import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import com.pluralsight.security.authn.CryptoOidcUser;

public class CryptoOidcUserService implements OAuth2UserService<OidcUserRequest, OidcUser> {

	private OidcUserService delegate = new OidcUserService();
	
	@Override
	public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		OidcUser user = delegate.loadUser(userRequest);
		return new CryptoOidcUser(user);
	}
	
}
