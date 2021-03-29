package com.pluralsight.security.handler;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class KeycloakLogoutHandler extends SecurityContextLogoutHandler{

	private Logger logger = LoggerFactory.getLogger(KeycloakLogoutHandler.class);
	private final WebClient webClient;
	
	public KeycloakLogoutHandler(WebClient webclient) {
		this.webClient=webclient;
	}
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		super.logout(request, response, authentication);
		logoutFromKeyCloak(authentication);
		
	}
	
	private void logoutFromKeyCloak(Authentication authentication) {
		OidcUser oidcUser = (OidcUser)authentication.getPrincipal();
		URI logoutUri = UriComponentsBuilder
			.fromUriString(oidcUser.getIssuer()+"/protocol/openid-connect/logout")
				.queryParam("id_token_hint", oidcUser.getIdToken().getTokenValue()).build().toUri();
		ClientResponse response = this.webClient.get().uri(logoutUri).exchange().doOnError(clientResponse -> clientResponse.printStackTrace()).block();
		logger.info("Log out response: "+response.statusCode());
	}
}
