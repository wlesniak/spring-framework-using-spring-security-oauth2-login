package com.pluralsight.security.oauth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.pluralsight.security.service.PortfolioCommandService;

@Service
public class CryptoOAuth2UserService extends DefaultOAuth2UserService{

	private final PortfolioCommandService portfolioService;
	
	public CryptoOAuth2UserService(PortfolioCommandService portfolioService) {
		this.portfolioService=portfolioService;
	}
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		if(!this.portfolioService.userHasAportfolio(oAuth2User.getName())) {
			this.portfolioService.createNewPortfolio(oAuth2User.getName());
		}
		return oAuth2User;
	}
	
}
