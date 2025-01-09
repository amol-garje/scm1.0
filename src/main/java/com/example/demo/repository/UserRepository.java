package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Entity.User;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User,String>{
        Optional<User> findByEmail(String email);
} 
