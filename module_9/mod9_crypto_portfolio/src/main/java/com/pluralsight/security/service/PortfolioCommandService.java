package com.pluralsight.security.service;

import com.pluralsight.security.model.AddTransactionToPortfolioDto;
import com.pluralsight.security.model.UserCommandWrapper;

public interface PortfolioCommandService {

	void createNewPortfolio(String username);
	boolean userHasAportfolio(String username);
	void addTransactionToPortfolio(UserCommandWrapper<AddTransactionToPortfolioDto> command);
	void removeTransactionFromPortfolio(UserCommandWrapper<String> command);
	
}

