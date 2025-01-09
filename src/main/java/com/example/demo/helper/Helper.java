package com.example.demo.helper;



import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;


public class Helper {
    
    public static String getEmailOfaLogedInUser(Authentication authentication){
        
        

        if(authentication instanceof  OAuth2AuthenticationToken){
               
            OAuth2AuthenticationToken tmp=(OAuth2AuthenticationToken)authentication;
            String str=tmp.getAuthorizedClientRegistrationId();
            String userName="";
            DefaultOAuth2User oth2=(DefaultOAuth2User)authentication.getPrincipal();

            if(str.equalsIgnoreCase("google")){
                     
                userName=oth2.getAttribute("email").toString();
            }else if(str.equalsIgnoreCase("github")){
                userName=oth2.getAttribute("email") != null ?oth2.getAttribute("email").toString():oth2.getAttribute("login").toString()+"@gmail.com";
            }
            return userName;

        }else{
            return authentication.getName();
        }
    }
}
