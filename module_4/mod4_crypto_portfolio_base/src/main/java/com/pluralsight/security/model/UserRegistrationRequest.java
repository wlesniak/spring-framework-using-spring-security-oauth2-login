package com.pluralsight.security.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.pluralsight.security.validation.PasswordConfirmed;
import com.pluralsight.security.validation.PasswordPolicy;
import com.pluralsight.security.validation.UniqueEmail;
import com.pluralsight.security.validation.UniqueUsername;


@PasswordConfirmed
public class UserRegistrationRequest {
		
	@NotEmpty(message="Please enter your firstname")
	private String firstname;
	@NotEmpty(message="Please enter your lastname")
	private String lastname;
	@NotEmpty(message="Please enter a username")
	@UniqueUsername
	private String username;
	@NotEmpty(message="Please enter an email")
	@Email(message="Email is not valid")
	@UniqueEmail
	private String email;
	@NotEmpty(message="Please enter in a password")
	@PasswordPolicy
	private String password;
	@NotEmpty(message="Please confirm your password")
	private String confirmPassword;
	@Min(4)
	private int securityPin;
	@Min(4)
	private int confirmSecurityPin;
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public int getSecurityPin() {
		return securityPin;
	}
	public void setSecurityPin(int securityPin) {
		this.securityPin = securityPin;
	}
	public int getConfirmSecurityPin() {
		return confirmSecurityPin;
	}
	public void setConfirmSecurityPin(int confirmSecurityPin) {
		this.confirmSecurityPin = confirmSecurityPin;
	}

}
