package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.User;

public interface UserService {
      User saveUser(User user);
      Optional<User> getUserById(String id);
      Optional<User> updateUser(User user);
      void deleteUser(String id);
      boolean isUserExit(String UserId);
      boolean isUserExitByEmail(String email);
      List<User> getAllUser();
      User getUserByEmail(String email);
      
}
