package com.example.security.controller;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.conf.JWTTokenHelper;
import com.example.security.service.UserService;
import com.example.security.vo.AuthorityVO;
import com.example.security.vo.LoginResponseVO;
import com.example.security.vo.UserVO;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Slf4j
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	JWTTokenHelper jWTTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/auth/login")
	public ResponseEntity<?> login(@RequestBody UserVO authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

		final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
				authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		
		log.info("authenticationRequest getUsername() : "+authenticationRequest.getUsername());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserVO user= (UserVO)authentication.getPrincipal();
		String jwtToken=jWTTokenHelper.generateToken(user.getUsername());
		
		LoginResponseVO response = new LoginResponseVO();
		response.setToken(jwtToken);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/auth/userinfo")
	public ResponseEntity<?> getUserInfo(Principal user){
		UserVO userObj= (UserVO) userDetailsService.loadUserByUsername(user.getName());
		List<AuthorityVO> list = userService.getAuth(user.getName());//name(x) id(o)
		userObj.setAuthorities(list);
		return ResponseEntity.ok(userObj);
	}
}
