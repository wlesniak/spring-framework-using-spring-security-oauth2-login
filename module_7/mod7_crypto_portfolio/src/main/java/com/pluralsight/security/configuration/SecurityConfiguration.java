package com.pluralsight.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.pluralsight.security.handler.CustomAuthorizationRequestResolver;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final ClientRegistrationRepository clientRegistrationRepository;
	
	public SecurityConfiguration(ClientRegistrationRepository clientRegistrationRepository) {
		this.clientRegistrationRepository=clientRegistrationRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
			.authorizeRequests()
				.anyRequest().authenticated()
			.and()
				.oauth2Login()
					.authorizationEndpoint()
						.authorizationRequestResolver(new CustomAuthorizationRequestResolver(this.clientRegistrationRepository))
					.and()
						.loginPage("/oauth2/authorization/crypto-portfolio")
				.and()
			.logout()
		.logoutUrl("/api/logout");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/webjars/**");
	}
	
}