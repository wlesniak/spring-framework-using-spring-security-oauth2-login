package com.pluralsight.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final AuthenticationSuccessHandler authenticationSuccessHandler;
	private final ClientRegistrationRepository clientRegistrationRepository;
	
	public SecurityConfiguration(AuthenticationSuccessHandler authenticationSuccessHandler, ClientRegistrationRepository clientRegistrationRepository) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
		this.clientRegistrationRepository=clientRegistrationRepository;
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.oauth2Login()
					.loginPage("/oauth2/authorization/crypto-portfolio")
					.successHandler(this.authenticationSuccessHandler)
					.and()
				.logout().logoutSuccessHandler(oidcLogoutSuccessHandler());		
	}
	
	private OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
		OidcClientInitiatedLogoutSuccessHandler successHandler = new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
		successHandler.setPostLogoutRedirectUri("http://localhost:8080/");
		return successHandler;
	}
	
}