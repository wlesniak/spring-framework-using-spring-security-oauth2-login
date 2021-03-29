package com.pluralsight.security.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final LogoutHandler logoutHandler;
	
	public SecurityConfiguration(LogoutHandler logoutHandler) {
		this.logoutHandler=logoutHandler;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf()
				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
			.and()
			.authorizeRequests()
				.mvcMatchers("/login").permitAll()
				.anyRequest().authenticated()
			.and()
				.oauth2Login()
					.loginPage("/oauth2/authorization/crypto-portfolio")
				.and()
			.logout().addLogoutHandler(this.logoutHandler)
		.logoutUrl("/api/logout");
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/webjars/**");
	}
	
}