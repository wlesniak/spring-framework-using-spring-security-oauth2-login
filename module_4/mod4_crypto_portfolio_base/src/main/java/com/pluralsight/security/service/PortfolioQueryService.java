package com.pluralsight.security.service;

import java.util.List;
import java.util.Optional;

import com.pluralsight.security.model.ListTransactionsDto;
import com.pluralsight.security.model.PortfolioPositionsDto;

public interface PortfolioQueryService {

	Optional<ListTransactionsDto> getPortfolioTransactionsForUser(String username);
	Optional<PortfolioPositionsDto> getPortfolioPositions(String id);
	List<String> getPortfolioIds();
	Optional<PortfolioPositionsDto> getPortfolioPositionsForUser(String username);
	
}
