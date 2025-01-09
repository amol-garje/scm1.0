package com.example.demo.Services.Impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Entity.User;
import com.example.demo.Services.UserService;
import com.example.demo.helper.ResourceNotFoundException;
import com.example.demo.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepo;


    @Override
    public User saveUser(User user) {
        String str=UUID.randomUUID().toString();   // Automatically Generate the String 
        user.setUserId(str);
        User save= userRepo.save(user);
        return save;
    }

    @Override
    public Optional<User> getUserById(String id) {
            return userRepo.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
          User usr=userRepo.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("User Not Found Here :"));   
    
          usr.setName(user.getName());
          usr.setEmail(user.getEmail());
          usr.setPassword(user.getPassword());
          usr.setAbout(user.getAbout());
          usr.setPhone(user.getPhone());
          usr.setProfilPic(user.getProfilPic());
          usr.setEnable(user.isEnable());
          usr.setEmailVerified(user.isEmailVerified());
          usr.setPhoneVerified(user.isPhoneVerified());
          usr.setProvider(user.getProvider());
          usr.setProviderUserId(user.getProviderUserId());

          return Optional.ofNullable(userRepo.save(usr));
    
    }

    @Override
    public void deleteUser(String id) {
        User us2=userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("not Found User"));
        userRepo.delete(us2);
    }

    @Override
    public boolean isUserExit(String UserId) {
        User usr=userRepo.findById(UserId).orElse(null);
        return usr!=null?true:false;
    }

    @Override
    public boolean isUserExitByEmail(String email) {
            User usr=userRepo.findByEmail(email).orElse(null);
            return usr!=null?true:false;
    } 

    @Override
    public List<User> getAllUser() {
       return userRepo.findAll();
    }

    @Override
    public User getUserByEmail(String email) {
       return userRepo.findByEmail(email).orElse(null);
    }

}
