package com.example.demo.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class securityConfig {

  
	// @SuppressWarnings("deprecation")
    // public PasswordEncoder getEncodedPassword() {
	// 	  return withDe;
	// }
	
	// If you Want to Add User Use this Method Only 
	@Bean
	public UserDetailsService getUser() {
		     UserDetails user1 = User.withDefaultPasswordEncoder().username("amol")
		     .password("amol@123")
		     .roles("ADMIN","USER")
		     .build();
		     
		     UserDetails user2 = User.withDefaultPasswordEncoder().username("atul")
		     .password("atul@123")
		     .roles("USER")
		     .build();
		     
		     return new InMemoryUserDetailsManager(user1,user2);
	}
	

    // If you Want to add the User and And Some Url Filter Condition then You Will Use the Security FilterChain 
    
    // @Bean
	// public SecurityFilterChain getSecurityChain(HttpSecurity http) throws Exception {

    //     http.authorizeHttpRequests((req) ->
    //             // req.requestMatchers("/admin").hasRole("ADMIN").
    //             req.requestMatchers("/user/*").hasRole("USER")
    //             .anyRequest().authenticated()
    //     ).formLogin();
	// 	  return http.build();
	// }
	

}
