package com.pluralsight.security.service;

import java.util.Collections;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pluralsight.security.entity.CryptoUser;
import com.pluralsight.security.entity.Portfolio;
import com.pluralsight.security.model.UserRegistrationRequest;
import com.pluralsight.security.repository.PortfolioRepository;
import com.pluralsight.security.repository.UserRepository;

@Service
public class UserRegistrationService {

	private final UserRepository userRepository;
	private final PortfolioRepository portfolioRepository;
	
	public UserRegistrationService(UserRepository userRepository, PortfolioRepository portfolioRepository) {
		this.userRepository = userRepository;
		this.portfolioRepository = portfolioRepository;
	}
	
	public void registerNewUser(UserRegistrationRequest userRegistrationRequest) {
		CryptoUser cryptoUser = new CryptoUser(userRegistrationRequest);
		userRepository.save(cryptoUser);
		portfolioRepository.save(new Portfolio(userRegistrationRequest.getUsername(), Collections.EMPTY_LIST));
	}
	
}
