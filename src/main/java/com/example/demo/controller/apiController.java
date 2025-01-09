package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.Contact;
import com.example.demo.Services.Impl.contactServiceImpleme;

@RestController
@RequestMapping("/api")
public class apiController {


    @Autowired
    contactServiceImpleme service;

    @GetMapping("/contact/{contactId}")
    public Contact getContact(@PathVariable String contactId){
             return service.getById(contactId);
    }


}
