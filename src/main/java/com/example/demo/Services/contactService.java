package com.example.demo.Services;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.Entity.Contact;
import com.example.demo.Entity.User;


public interface contactService {
    Contact save(Contact con);

    Contact update(Contact con);
    
    List<Contact> getAll();

    Contact getById(String id);

    void delete(String id);

    Page<Contact> searchByName(String nameKeyword,int size,int page,String sortBy,String order,User usr);
    Page<Contact> searchByEmail(String emailKeyword,int size,int page,String sortBy,String order,User usr);
    Page<Contact> searchByPhoneNumber(String pnoneNumberKeyword,int size,int page,String sortBy,String order,User usr);

    List<Contact> getbyUserId(String id);

    Page<Contact> getByUser(User user,int page,int size,String sortBy, String direction);
}
