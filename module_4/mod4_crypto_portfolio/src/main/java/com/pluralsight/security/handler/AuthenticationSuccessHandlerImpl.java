package com.pluralsight.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.pluralsight.security.authn.CryptoPrincipal;
import com.pluralsight.security.model.UserRegistrationRequest;
import com.pluralsight.security.service.PortfolioQueryService;
import com.pluralsight.security.service.UserRegistrationService;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

	private PortfolioQueryService portfolioQueryService;
	private UserRegistrationService userRegistrationService;

	public AuthenticationSuccessHandlerImpl(PortfolioQueryService portfolioQueryService,
			UserRegistrationService userRegistrationService) {
		this.portfolioQueryService = portfolioQueryService;
		this.userRegistrationService = userRegistrationService;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String username = authentication.getName();
		if (portfolioQueryService.getPortfolioPositionsForUser(username).isEmpty()) {
			if (authentication instanceof OAuth2AuthenticationToken) {
				CryptoPrincipal principal = (CryptoPrincipal)authentication.getPrincipal();
				UserRegistrationRequest registrationRequest = new UserRegistrationRequest();
				registrationRequest.setUsername(username);
				registrationRequest.setEmail(principal.getEmail());
				registrationRequest.setFirstname(principal.getFirstName());
				registrationRequest.setLastname(principal.getLastName());
				this.userRegistrationService.registerNewUser(registrationRequest);
			}
		}
		response.sendRedirect("/portfolio");

	}

}
