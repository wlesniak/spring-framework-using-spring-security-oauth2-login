package com.pluralsight.security.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.pluralsight.security.service.CryptoOidcUserService;
import com.pluralsight.security.userinfo.FacebookUser;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private AuthenticationSuccessHandler authenticationSuccessHandler;
	
	public SecurityConfiguration(AuthenticationSuccessHandler authenticationSuccessHandler) {
		this.authenticationSuccessHandler = authenticationSuccessHandler;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.mvcMatchers("/login","/register").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.and()
			.logout()
			.and()
			.oauth2Login()
				.loginPage("/login")
				.successHandler(this.authenticationSuccessHandler)
				.userInfoEndpoint().customUserType(FacebookUser.class, "facebook")
				.oidcUserService(new CryptoOidcUserService());
	}
	

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/webjars/**");
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		DelegatingPasswordEncoder encoder =  (DelegatingPasswordEncoder)PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;	
	}	

	
	
	//@Override
	protected void configure2(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.mvcMatchers("/login","/register").permitAll()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")
			.and()
			.logout()
			.and()
			.oauth2Login()
				.successHandler(authenticationSuccessHandler)
				.loginPage("/login");
				//.userInfoEndpoint().customUserType(FacebookUser.class, "facebook")
				//.oidcUserService(new CryptoOidcUserService());
	}
	
}