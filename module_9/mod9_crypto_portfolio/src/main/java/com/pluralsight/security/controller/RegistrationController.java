package com.pluralsight.security.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.pluralsight.security.model.UserRegistrationRequest;
import com.pluralsight.security.service.UserRegistrationService;

@Controller
public class RegistrationController {

	private final UserRegistrationService registrationService;
	
	public RegistrationController(UserRegistrationService registrationService) {
		this.registrationService=registrationService;
	}

	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user",new UserRegistrationRequest());
		return "register";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") UserRegistrationRequest userRegistration, BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}	
		registrationService.registerNewUser(userRegistration);
		return "redirect:register?success";
	}
	
}
