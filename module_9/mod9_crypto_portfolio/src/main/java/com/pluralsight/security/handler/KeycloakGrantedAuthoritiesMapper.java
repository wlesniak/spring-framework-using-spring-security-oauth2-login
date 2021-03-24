package com.pluralsight.security.handler;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.stereotype.Component;

@Component
public class KeycloakGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper{

	@Override
	public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
		Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
		authorities.forEach(authority -> {
			if(OidcUserAuthority.class.isInstance(authority)) {
				OidcUserAuthority userAuthority = (OidcUserAuthority) authority;
				List<String> roles = (List<String>)userAuthority.getUserInfo().getClaimAsStringList("roles");
				mappedAuthorities.addAll(roles.stream().map(role -> "ROLE_"+role).map(SimpleGrantedAuthority::new)
						.collect(Collectors.toSet()));
			} else {
				mappedAuthorities.add(authority);
			}
			
		});
		return mappedAuthorities;
	}

}
