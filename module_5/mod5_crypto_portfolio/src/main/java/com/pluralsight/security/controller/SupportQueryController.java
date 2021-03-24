package com.pluralsight.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.pluralsight.security.model.CreateSupportQueryDto;
import com.pluralsight.security.model.PostDto;
import com.pluralsight.security.service.SupportQueryService;

@Controller
public class SupportQueryController {
		
	private final SupportQueryService supportService;
				
	public SupportQueryController(SupportQueryService supportService) {
		this.supportService = supportService;
	}

	@GetMapping("/support")
	public ModelAndView getQueries() {
		return new ModelAndView("support","queries",supportService.getSupportQueriesForUser());
	}

	@GetMapping("/support/query/{id}")
	public ModelAndView getQuery(@PathVariable String id) {
		return supportService.getSupportQueryById(id).map(query -> {
			ModelAndView model = new ModelAndView("query","query",query);
			PostDto newPost = new PostDto();
			newPost.setResolve(query.isResolved());
			model.addObject("newPost",new PostDto());
			return model;
		}).orElseGet(() -> new ModelAndView("query"));
	}	
	
	@GetMapping("/support/compose")
	public ModelAndView createNewSupportQuery() {
		ModelAndView model = new ModelAndView();
		model.addObject("newQuery", new CreateSupportQueryDto());
		model.setViewName("compose");
		return model;
	}
	
}
