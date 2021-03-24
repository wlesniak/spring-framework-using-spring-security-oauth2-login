package com.pluralsight.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pluralsight.security.repository.UserRepository;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	private UserRepository userRepository;
	
	public UniqueUsernameValidator(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public boolean isValid(String username, ConstraintValidatorContext context) {
		return username != null && this.userRepository.findByUsername(username) == null ;
	}

}
