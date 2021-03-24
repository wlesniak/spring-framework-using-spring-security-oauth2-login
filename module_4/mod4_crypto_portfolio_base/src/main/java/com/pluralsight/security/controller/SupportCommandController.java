package com.pluralsight.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.pluralsight.security.authn.CustomUserDetails;
import com.pluralsight.security.model.CreateSupportQueryDto;
import com.pluralsight.security.model.PostDto;
import com.pluralsight.security.model.UserCommandWrapper;
import com.pluralsight.security.service.SupportCommandService;

@Controller
public class SupportCommandController {

	private final SupportCommandService supportService;
	
	public SupportCommandController(SupportCommandService supportService) {
		this.supportService = supportService;
	}

	@PostMapping("/support")
	public String createNewQuery(@ModelAttribute CreateSupportQueryDto createSupportQueryDto, @AuthenticationPrincipal CustomUserDetails principal) {
		supportService.createQuery(new UserCommandWrapper<CreateSupportQueryDto>(principal,createSupportQueryDto));
		return "redirect:/support";
	}

	@PostMapping("/support/query/{id}")
	public String postToQuery(@ModelAttribute PostDto postDto, @PathVariable String id,  @AuthenticationPrincipal CustomUserDetails principal) {
		postDto.setQueryId(id);
		supportService.postToQuery(new UserCommandWrapper<PostDto>(principal, postDto));
		return "redirect:/support/query/"+postDto.getQueryId();
	}
	
}
