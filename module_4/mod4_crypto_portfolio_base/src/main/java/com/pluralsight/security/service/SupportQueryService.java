package com.pluralsight.security.service;

import java.util.List;
import java.util.Optional;

import com.pluralsight.security.model.SupportQueryDto;

public interface SupportQueryService {

	List<SupportQueryDto> getSupportQueriesForUser();
	Optional<SupportQueryDto> getSupportQueryById(String queryId);
	List<SupportQueryDto> getSupportQueriesForAllUsers();
	
}
