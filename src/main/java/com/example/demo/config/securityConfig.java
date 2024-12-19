package com.example.demo.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class securityConfig {

  
	// @SuppressWarnings("deprecation")
    // public PasswordEncoder getEncodedPassword() {
	// 	  return withDe;
	// }
	
	// If you Want to Add User Use this Method Only 
	// @Bean
	// public UserDetailsService getUser() {
	// 	     UserDetails user1 = User.withDefaultPasswordEncoder().username("amol")
	// 	     .password("amol@123")
	// 	     .roles("ADMIN","USER")
	// 	     .build();
		     
	// 	     UserDetails user2 = User.withDefaultPasswordEncoder().username("atul")
	// 	     .password("atul@123")
	// 	     .roles("USER")
	// 	     .build();
		     
	// 	     return new InMemoryUserDetailsManager(user1,user2);
	// }
	

    // If you Want to add the User and And Some Url Filter Condition then You Will Use the Security FilterChain 
    
    @Bean
	public SecurityFilterChain getSecurityChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((req) ->
                // req.requestMatchers("/admin").hasRole("ADMIN").
                req.requestMatchers("/user/*").hasRole("USER")
				.requestMatchers("/*").permitAll()
                .anyRequest().authenticated()
        ).formLogin();
		  return http.build();
	}


	 @Autowired
	public UserDetailsService userDetailsService;
	
    BCryptPasswordEncoder tmp=new BCryptPasswordEncoder(12);
	
	 @Bean
	    public AuthenticationProvider authenticationProvider() {
	    	DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
//	    	provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
	    	provider.setPasswordEncoder(tmp);
	    	provider.setUserDetailsService(userDetailsService);
	    	return provider;
	    }



}
