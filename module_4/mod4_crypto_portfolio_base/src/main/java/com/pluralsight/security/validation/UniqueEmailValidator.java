package com.pluralsight.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pluralsight.security.repository.UserRepository;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	private UserRepository userRepository;
	
	public UniqueEmailValidator(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return email != null && this.userRepository.findByEmail(email) == null ;
	}

}
