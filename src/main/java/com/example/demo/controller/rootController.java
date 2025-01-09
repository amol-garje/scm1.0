package com.example.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.Entity.User;
import com.example.demo.Services.Impl.UserServiceImpl;
import com.example.demo.helper.Helper;

@ControllerAdvice  // this class all method are execute all controller
public class rootController {

    private Logger loger = LoggerFactory.getLogger(userController.class);

    @Autowired
    UserServiceImpl impl;

    @ModelAttribute    // I Spring Web MVC If You Want to Execute this Method Before Executing Other Method we Use this Annotation 
    public void addLogonUserInfromation(Model m,Authentication auth){  

        if(auth==null){
             return;
        }

        String str=Helper.getEmailOfaLogedInUser(auth);
        loger.info("User Login "+str);   
        User usr=impl.getUserByEmail(str);
        System.out.println("The UseController Class is Here :-->"+usr.getEmail());
        System.out.println("The UserController Class is Here :-->"+usr.getName());
        m.addAttribute("uInfo", usr);
    }
}
