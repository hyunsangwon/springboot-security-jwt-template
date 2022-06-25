package com.example.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security.mapper.UserMapper;
import com.example.security.vo.AuthorityVO;
import com.example.security.vo.UserVO;

@Service
public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public int setUser(UserVO usersVO) {
		String password = usersVO.getUserPassword();
		password = passwordEncoder.encode(password);
		usersVO.setUserPassword(password);
		return userMapper.insertUser(usersVO);
	}

	public List<AuthorityVO> getAuth(String userId){
		return userMapper.selectUserAuth(userId);
	}
	
}
