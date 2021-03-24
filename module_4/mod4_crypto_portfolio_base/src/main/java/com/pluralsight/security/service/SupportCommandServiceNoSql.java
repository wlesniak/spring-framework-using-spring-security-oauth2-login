package com.pluralsight.security.service;

import org.springframework.stereotype.Service;

import com.pluralsight.security.entity.Post;
import com.pluralsight.security.entity.SupportQuery;
import com.pluralsight.security.model.CreateSupportQueryDto;
import com.pluralsight.security.model.PostDto;
import com.pluralsight.security.model.UserCommandWrapper;
import com.pluralsight.security.repository.SupportQueryRepository;

@Service
public class SupportCommandServiceNoSql implements SupportCommandService {

	private final SupportQueryRepository supportRepository;
		
	public SupportCommandServiceNoSql(SupportQueryRepository supportRepository) {
		this.supportRepository = supportRepository;
	}

	@Override
	public void createQuery(UserCommandWrapper<CreateSupportQueryDto> command) {
		supportRepository.save(mapModelToEntity(command.getUserDetails().getUsername(),command.getDto()));
	}
	
	@Override
	public void postToQuery(UserCommandWrapper<PostDto> command) {
		PostDto postRequest = command.getDto();
		Post post = new Post(command.getUserDetails().getUsername() , postRequest.getContent(), System.currentTimeMillis());
		supportRepository.findById(postRequest.getQueryId()).ifPresent(query -> {
			query.addPost(post);
			if(postRequest.isResolve()) {
				query.resolve();
			}
			supportRepository.save(query);
		});
		
	}
	
	@Override
	public void resolveQuery(String id) {
		supportRepository.findById(id).ifPresent(query -> {
			query.resolve();
			supportRepository.save(query);
		});

	}
	
	private SupportQuery mapModelToEntity(String username, CreateSupportQueryDto model) {
		SupportQuery supportQuery = new SupportQuery(username, model.getSubject());
		supportQuery.addPost(model.getContent(), username );
		return supportQuery;
	}
		
}
