package com.pluralsight.security.authn;

import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

public class CryptoOidcUser extends DefaultOidcUser implements CryptoPrincipal{

	public CryptoOidcUser(OidcUser oidcUser) {
		super(oidcUser.getAuthorities(), oidcUser.getIdToken(), oidcUser.getUserInfo());
	}

	@Override
	public String getUsername() {
		return getSubject();
	}

	@Override
	public String getFirstName() {
		return super.getGivenName();
	}

	@Override
	public String getLastName() {
		return super.getFamilyName();
	}

	@Override
	public String getFullName() {
		return super.getFullName();
	}

	@Override
	public String getEmail() {
		return super.getEmail();
	}

}
