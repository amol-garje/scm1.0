package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Contact;
import com.example.demo.Entity.User;

@Repository
public interface contactReposi extends JpaRepository<Contact,String>{

    public Page<Contact> findByUser(User usr,Pageable pageable);

    @Query("SELECT c FROM Contact c WHERE c.id= :userId ")
    public List<Contact> findByUserId(@Param("userId")String userId);


    Page<Contact> findByUserAndNameContaining(User usr,String namekeyword,Pageable pageable);
    Page<Contact> findByUserAndEmailContaining(User usr,String emailkeyword,Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining(User usr,String phoneNumberkeyword,Pageable pageable);

}
