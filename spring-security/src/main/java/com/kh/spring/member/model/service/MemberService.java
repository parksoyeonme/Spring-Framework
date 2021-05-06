package com.kh.spring.member.model.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService{

	//public static final
	String ROLE_USER = "USER";
	String ROLE_ADMIN = "ADMIN";
}
