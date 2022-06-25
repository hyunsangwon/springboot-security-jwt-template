package com.example.security;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.security.vo.UserVO;

@SpringBootApplication
public class SecurityApplication {

//	@Autowired
//	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(SecurityApplication.class, args);
	}

	@PostConstruct
	protected void init() {
		UserVO vo = new UserVO();
		vo.setUserId("sangwon");
		vo.setUserPassword("123");
		vo.setName("Sangwon Hyun");
		vo.setEmail("hyunsangwon93@gmail.com");
		//userService.setUser(vo);
	}
}
