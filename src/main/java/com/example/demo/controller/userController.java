package com.example.demo.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.Entity.User;

import com.example.demo.Services.Impl.UserServiceImpl;
import com.example.demo.helper.Helper;



@Controller
@RequestMapping("/user")
public class userController {
    
    // User DashBord Page

    @Autowired
    UserServiceImpl impl;

    private Logger loger = LoggerFactory.getLogger(userController.class);

    @RequestMapping(value="/dashbord", method=RequestMethod.POST)
    public String userDashBord(Model m){
         return "/user/dashbord";
    }

    @RequestMapping(value="/dashbord", method=RequestMethod.GET)
    public String userDashBor(Model m){
         return "/user/dashbord";
    }


    // User Profile
    @RequestMapping(value="/profile", method=RequestMethod.POST)
    public String userProfile(Authentication pl,Model m){
       
        return "/user/profile";
    }

    @RequestMapping(value="/profile", method=RequestMethod.GET)
    public String userProfil(Authentication pl,Model m){

        return "/user/profile";
    }
    // @ModelAttribute    // I Spring Web MVC If You Want to Execute this Method Before Executing Other Method we Use this Annotation 
    // public void addLogonUserInfromation(Model m,Authentication auth){  
    //     String str=Helper.getEmailOfaLogedInUser(auth);
    //     loger.info("User Login "+str);   
    //     User usr=impl.getUserByEmail(str);
    //     System.out.println("The UseController Class is Here :-->"+usr.getEmail());
    //     System.out.println("The UserController Class is Here :-->"+usr.getName());
    //     m.addAttribute("uInfo", usr);
    // }
    

}
