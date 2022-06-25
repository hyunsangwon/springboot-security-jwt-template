 package com.example.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.security.mapper.UserMapper;
import com.example.security.vo.UserVO;

@Service
public class AuthService implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
		
		UserVO userVO = userMapper.selectUserInfo(userId);
		
		if(userVO == null) {
			throw new UsernameNotFoundException("User Not Found with userId "+userId);
		}
		return userVO;
	}
	
}
