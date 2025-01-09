package com.example.demo.Services;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.Entity.User;

public class userPrinciple implements UserDetails{

    User usr;
    public userPrinciple(User usr) {
        this.usr = usr;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       	return usr.getRoles().stream()
	            .map(r -> new SimpleGrantedAuthority("ROLE_" + r.getRole()))
	            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return usr.getPassword();
    }

    @Override
    public String getUsername() {
        return usr.getEmail();
    }

}
