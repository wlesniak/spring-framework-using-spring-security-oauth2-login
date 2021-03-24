package com.pluralsight.security.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.pluralsight.security.model.UserRegistrationRequest;

public class PasswordConfirmedValidator implements ConstraintValidator<PasswordConfirmed, Object>{

	@Override
	public boolean isValid(Object user, ConstraintValidatorContext context) {
		String password = ((UserRegistrationRequest)user).getPassword();
		String confirmedPassword = ((UserRegistrationRequest)user).getConfirmPassword();
		return password.equals(confirmedPassword);
	}

}
