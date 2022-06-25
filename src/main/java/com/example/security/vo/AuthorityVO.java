package com.example.security.vo;

import org.springframework.security.core.GrantedAuthority;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorityVO implements GrantedAuthority{
	
	private Long id;
	private String roleCode;
	private String roleDescription;
	
	@Override
	public String getAuthority() {
		return this.roleCode;
	}
	
}
