package com.pluralsight.security.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.pluralsight.security.annotations.FilterOutNonCurrentUser;
import com.pluralsight.security.entity.SupportQuery;
import com.pluralsight.security.model.PostDto;
import com.pluralsight.security.model.SupportQueryDto;
import com.pluralsight.security.repository.SupportQueryRepository;

@Service
public class SupportQueryServiceNoSql implements SupportQueryService {

	private final SupportQueryRepository supportRepository;
	
	public SupportQueryServiceNoSql(SupportQueryRepository supportRepository) {
		this.supportRepository = supportRepository;
	}

	@Override
	@FilterOutNonCurrentUser
	public List<SupportQueryDto> getSupportQueriesForUser() {
		List<SupportQuery> supportQueries = supportRepository.findByUsername(getUsername());
		return mapEntityToModel(supportQueries);
	}

	@Override
	public Optional<SupportQueryDto> getSupportQueryById(String id) {
		return this.supportRepository.findById(id).map(query -> {
			return mapEntityToModel(query);
		});
	}
	
	@Override
	public List<SupportQueryDto> getSupportQueriesForAllUsers() {
		List<SupportQuery> supportQueries = this.supportRepository.findAll();
		return mapEntityToModel(supportQueries);
	}
	
	private SupportQueryDto mapEntityToModel(SupportQuery supportQuery) {
		List<PostDto> posts = supportQuery.getPosts().stream().map(post -> {
			return new PostDto(post.getId(), post.getContent(),post.getUsername(),supportQuery.isResolved());
		}).collect(Collectors.toList());
		return new SupportQueryDto(supportQuery.getId(), supportQuery.getSubject(), supportQuery.getCreated(),
				supportQuery.getUsername(), supportQuery.isResolved(), posts);

	}
	
	private String getUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	private List<SupportQueryDto> mapEntityToModel(List<SupportQuery> supportQueries) {
		return supportQueries.stream().map(query -> {
			return mapEntityToModel(query);
		}).collect(Collectors.toList());
	}

}