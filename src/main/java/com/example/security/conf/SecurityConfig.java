package com.example.security.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security.service.AuthService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthService authService;
	@Autowired
	private JWTTokenHelper jWTTokenHelper;
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)throws Exception {
		//in-memory, db 둘다 사용불가능
		//In-memory
		auth.inMemoryAuthentication().withUser("sama").password("123").authorities("USER","ADMIN");
		
		//Database Auth
		auth.userDetailsService(authService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().authenticated();
		//http.httpBasic();
		  http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		  .and()
		  .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
		  .and()
		  .authorizeRequests((request) -> request.antMatchers("/api/v1/auth/login").permitAll()
					.antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated())
		  .addFilterBefore(new JWTAuthenticationFilter(authService, jWTTokenHelper),
					UsernamePasswordAuthenticationFilter.class);
		
		  http.csrf().disable().cors()
		  .and()
		  .headers().frameOptions().disable();
	}
	
}