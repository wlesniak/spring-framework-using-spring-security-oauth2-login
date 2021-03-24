package com.pluralsight.security.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pluralsight.security.authn.CustomUserDetails;
import com.pluralsight.security.entity.CryptoUser;
import com.pluralsight.security.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;	

	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<CryptoUser> userOptional = this.userRepository.findByUsername(username);
		return userOptional.map(user -> {
			List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
			authList.add(new SimpleGrantedAuthority("USER"));
			return new CustomUserDetails(user, authList);
		}).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
