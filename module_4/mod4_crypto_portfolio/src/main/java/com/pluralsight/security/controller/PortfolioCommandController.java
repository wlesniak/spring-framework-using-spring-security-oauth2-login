package com.pluralsight.security.controller;


import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.pluralsight.security.authn.CryptoPrincipal;
import com.pluralsight.security.model.AddTransactionToPortfolioDto;
import com.pluralsight.security.model.DeleteTransactionsDto;
import com.pluralsight.security.model.UserCommandWrapper;
import com.pluralsight.security.service.PortfolioCommandService;

@Controller
public class PortfolioCommandController {

	private final PortfolioCommandService commandService;
	
	public PortfolioCommandController(PortfolioCommandService commandService) {
		this.commandService = commandService;
	}

	@PostMapping("/portfolio/transactions")
	public ModelAndView addTransactionToPortfolio(@ModelAttribute("transaction") AddTransactionToPortfolioDto request, Authentication authentication) {	
		commandService.addTransactionToPortfolio(new UserCommandWrapper<AddTransactionToPortfolioDto>((CryptoPrincipal)authentication.getPrincipal(), request));
		return new ModelAndView("redirect:/portfolio");
	}
	
	@DeleteMapping("/portfolio/transactions")
	public ModelAndView deleteTransactionFromPortfolio(@ModelAttribute("selected") DeleteTransactionsDto request, Authentication authentication) {
		for(String id : request.getId()) {
			commandService.removeTransactionFromPortfolio(new UserCommandWrapper<String>((CryptoPrincipal)authentication.getPrincipal(), id));
		}
		return new ModelAndView("redirect:/portfolio");
	}
	
}
