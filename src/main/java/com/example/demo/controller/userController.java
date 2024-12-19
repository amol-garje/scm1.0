package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
@RequestMapping("/user")
public class userController {
    
    // User DashBord Page

    @RequestMapping(value="/dashbord", method=RequestMethod.GET)
    public String userDashBord(Model m){
         return "user/dashbord";
    }


    // User Profile
    @RequestMapping(value="/profile", method=RequestMethod.GET)
    public String userProfile(Model m){
         return "user/profile";
    }
    

}
