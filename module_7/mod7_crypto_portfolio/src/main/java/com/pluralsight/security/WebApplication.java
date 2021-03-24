package com.pluralsight.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.pluralsight.security.entity.CryptoCurrency;
import com.pluralsight.security.entity.CryptoUser;
import com.pluralsight.security.repository.CryptoCurrencyRepository;
import com.pluralsight.security.repository.PortfolioRepository;
import com.pluralsight.security.repository.UserRepository;

@SpringBootApplication
public class WebApplication {

	private final CryptoCurrencyRepository cryptoRepository;
	private final UserRepository userRepository;
	
	public WebApplication(CryptoCurrencyRepository cryptoRepository,UserRepository userRepository) {
		this.cryptoRepository = cryptoRepository;
		this.userRepository=userRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	public void intializeUserData() {		
		CryptoCurrency bitcoin = new CryptoCurrency("BTC", "Bitcoin");
		CryptoCurrency litecoin = new CryptoCurrency("LTC", "Litecoin");
		cryptoRepository.save(bitcoin);
		cryptoRepository.save(litecoin);
		CryptoUser user = new CryptoUser();
		user.setId("12344567");
		user.setPassword("{noop}password");
		user.setUsername("victoria");
		this.userRepository.save(user);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
}