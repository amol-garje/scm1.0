package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class userDetailService implements UserDetailsService{


    @Autowired
    private UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User usr=repo.findByEmail(username).get();
        // System.out.println("your Email Come is "+usr.getEmail());
        if(usr==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("User Not Found");
        }
        System.out.println("your Email Come is "+usr.getEmail());
        return new userPrinciple(usr);
    }

}
