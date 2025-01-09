package com.example.demo.Services.Impl;

import java.awt.print.Pageable;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Contact;
import com.example.demo.Entity.User;
import com.example.demo.Services.contactService;
import com.example.demo.helper.ResourceNotFoundException;
import com.example.demo.repository.contactReposi;

@Service
public class contactServiceImpleme implements contactService{

    @Autowired
    private contactReposi repo;

    @Override
    public Contact save(Contact con) {
        String str=UUID.randomUUID().toString();
        con.setId(str);
        return repo.save(con);
    }

    @Override
    public Contact update(Contact con) {
        Contact oldContact= repo.findById(con.getId()).orElseThrow(()->new ResourceNotFoundException("Contact not found"));
        oldContact.setId(con.getId());
        oldContact.setAddress(con.getAddress());
        oldContact.setName(con.getName());
        oldContact.setPhoneNumber(con.getPhoneNumber());
        oldContact.setDescription(con.getDescription());
        oldContact.setEmail(con.getEmail());
        oldContact.setFavorite(con.isFavorite());
        oldContact.setWebsiteLink(con.getWebsiteLink());
        oldContact.setLinkedinLink(con.getLinkedinLink());
        oldContact.setPicture(con.getPicture());
        oldContact.setCloudinaryImagePublicid(con.getCloudinaryImagePublicid());
        oldContact.setContacts(con.getContacts());

        return repo.save(oldContact);
    
    
    }

    @Override
    public List<Contact> getAll() {
          return repo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Your Data is Not Present"));
    }

    @Override
    public void delete(String id) {

        System.out.println("done ,, done ,, done ,, done"+id);
        repo.deleteById(id);
    }

   

    @Override
    public List<Contact> getbyUserId(String id) {
           return repo.findByUserId(id);
    }

    @Override
    public Page<Contact> getByUser(User user,int page,int size,String sortBy, String direction) {

        Sort sort=direction.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();

        PageRequest pgr=PageRequest.of(page, size,sort);

      return repo.findByUser(user, pgr);
    }

    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order,User usr) {
        Sort sort=order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        PageRequest pagable=PageRequest.of(page, size,sort);

        return repo.findByUserAndNameContaining(usr,nameKeyword,pagable);
    }

    @Override
    public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,User usr) {
        Sort sort=order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
        PageRequest pagable=PageRequest.of(page, size,sort);

        return repo.findByUserAndEmailContaining(usr,emailKeyword,pagable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String pnoneNumberKeyword, int size, int page, String sortBy,String order,User usr) {
                Sort sort=order.equals("desc")?Sort.by(sortBy).descending():Sort.by(sortBy).ascending();
                PageRequest pagable=PageRequest.of(page, size,sort);
        
                return repo.findByUserAndPhoneNumberContaining(usr,pnoneNumberKeyword,pagable);
    }
}
