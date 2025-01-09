package com.example.demo.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.example.demo.Entity.User;
import com.example.demo.Entity.userRoles;
import com.example.demo.repository.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class authenticateSuccessHandler implements AuthenticationSuccessHandler{

    Logger logr=LoggerFactory.getLogger(authenticateSuccessHandler.class);


    @Autowired
    UserRepository repo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
            logr.info("Outhentication Handle");

            DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();

            OAuth2AuthenticationToken tokenAut=(OAuth2AuthenticationToken)authentication;

            user.getAttributes().forEach((key,value)->{
                logr.info(key+"-->"+value);
            });

            String str=tokenAut.getAuthorizedClientRegistrationId();
            logr.info("your provider is :==>"+str);


            User usr=new User();
            usr.setUserId(UUID.randomUUID().toString());
            usr.setRoles(List.of(new userRoles("USER", usr)));
            usr.setEmailVerified(true);
            usr.setEnable(true);
            usr.setProvider(str);
            

            if(str.equalsIgnoreCase("Google")){
              

                usr.setEmail(user.getAttribute("email").toString());
                usr.setProfilPic(user.getAttribute("picture").toString());
                usr.setName(user.getAttribute("name").toString());
                usr.setProviderUserId(user.getName());
                // usr.setProvider(tokenAut.);


            }else if(str.equalsIgnoreCase("github")){
               

                String email=user.getAttribute("email") != null ?user.getAttribute("email").toString():user.getAttribute("login").toString()+"@gmail.com";
                String picture=user.getAttribute("avatar_url").toString();
                String name=user.getAttribute("login").toString();
                String providerUserId=user.getName();

                usr.setEmail(email);
                usr.setProfilPic(picture);
                usr.setName(name);
                usr.setProviderUserId(providerUserId);
            }else{

            }






            // String email=user.getAttribute("email").toString();
            // String name=user.getAttribute("name").toString();
            // String picture=user.getAttribute("picture").toString();

            // User  usr=new User();
            // usr.setEmail(email);
            // usr.setName(name);
            // usr.setProfilPic(picture);
            // usr.setPassword("password");
            // usr.setUserId(UUID.randomUUID().toString());
            // usr.setProvider(Provider.GOOGLE);
            // usr.setEnable(true);
            // usr.setEmailVerified(true);
            // usr.setProviderUserId(user.getName());
            // // usr.setRoles(Lisy);
            // usr.setAbout("this Account is Crreated by Using the Google");


            User db=repo.findByEmail(usr.getEmail()).orElse(null);
            if(db==null){
                repo.save(usr);
                logr.info("Your User is Saved Sucessfuly");
            }


            new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }
}
