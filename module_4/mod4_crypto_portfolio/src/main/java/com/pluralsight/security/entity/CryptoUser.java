package com.pluralsight.security.entity;

import javax.validation.constraints.Email;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.pluralsight.security.model.UserRegistrationRequest;

@Document
public class CryptoUser {

		@Id
		private String id;
		@Indexed(unique=true)
		private String username;
		private String firstName;
		private String lastName;
		@Email
		private String email;
		private String password;
		
		public CryptoUser() {}
		
		public CryptoUser(UserRegistrationRequest userRegistration, PasswordEncoder passwordEncoder) {
			this.username=userRegistration.getUsername();
			this.password=userRegistration.getPassword();
			this.email=userRegistration.getEmail();
			this.firstName=userRegistration.getFirstname();
			this.lastName=userRegistration.getLastname();
			if(password!=null) {
				this.password=passwordEncoder.encode(password);
			}
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
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
		@Override
		public String toString() {
			return "CryptoUser [id=" + id + ", username=" + username + ", firstName=" + firstName + ", lastName="
					+ lastName + ", email=" + email + ", password=" + password + ", verified=" + "]";
		}
			
	}


