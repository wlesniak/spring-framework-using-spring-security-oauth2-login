package com.pluralsight.security.service;

import com.pluralsight.security.model.CreateSupportQueryDto;
import com.pluralsight.security.model.PostDto;
import com.pluralsight.security.model.UserCommandWrapper;

public interface SupportCommandService {

	void createQuery(UserCommandWrapper<CreateSupportQueryDto> query);
	void postToQuery(UserCommandWrapper<PostDto> supportQueryPostModel);
	void resolveQuery(String id);
	
}
