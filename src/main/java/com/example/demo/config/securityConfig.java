package com.example.demo.config;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

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
	
	@Autowired
	private authenticateSuccessHandler hadler;

    // If you Want to add the User and And Some Url Filter Condition then You Will Use the Security FilterChain 
    
    @Bean
	public SecurityFilterChain getSecurityChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((req) ->
                // req.requestMatchers("/admin").hasRole("ADMIN").
                // req.requestMatchers("/user/*").hasRole("USER")
				req.requestMatchers("/user/**").authenticated()
				.anyRequest().permitAll()
        ).formLogin(formLogin->{

			formLogin.loginPage("/login");  // this is a path where the oure logine page is present
			formLogin.loginProcessingUrl("/authenticate");  // this Authenticate is a Predefine 
			formLogin.successForwardUrl("/user/profile");
			// formLogin.failureForwardUrl("/login?error=true");

			formLogin.usernameParameter("email");  // same name in form . name='email'
			formLogin.passwordParameter("password"); //// same name in form . name='password'
            
			
			// formLogin.failureHandler(new AuthenticationFailureHandler() {
			// 	@Override
			// 	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			// 			AuthenticationException exception) throws IOException, ServletException {
					
			// 		throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationFailure'");
			// 	}
			// });

			// formLogin.successHandler(new AuthenticationSuccessHandler() {
			// 	@Override
			// 	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			// 			Authentication authentication) throws IOException, ServletException {
			// 		// TODO Auto-generated method stub
			// 		throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
			// 	}
			// });


		});  // ethun baiDefault Form tayar Krun Yeto jr aaplyala nvin form Pahije Asel tr aapn ethun krto
		
		

		http.logout(logout->{
			   logout.logoutUrl("/logout");
			   logout.logoutSuccessUrl("/login?logout=true");
		});

		// ouath login  ....

		http.oauth2Login(oauth->{
			oauth.loginPage("/login");
			oauth.successHandler(hadler);
		});

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
