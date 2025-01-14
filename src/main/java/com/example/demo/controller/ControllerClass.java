package com.example.demo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.BindingData.UserForm;
import com.example.demo.Entity.User;
import com.example.demo.Entity.userRoles;
import com.example.demo.Services.UserService;
import com.example.demo.helper.Message;
import com.example.demo.helper.MessageType;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class ControllerClass {


    @Autowired
    UserService service;

    BCryptPasswordEncoder encode=new BCryptPasswordEncoder(12);


    @GetMapping("/")
    public String welcomePage(){
        return "redirect:/home";    // redirect to Another Page ....
    }


    // 
    @RequestMapping("/home")
    public String homePage(Model m){
         return "home";
    }

    @RequestMapping("/service")
    public String servicePage(Model m){
         return "service";
    }

    @RequestMapping(value ="/about", method=RequestMethod.GET)
    public String aboutPage() {
        return "about";
    }

    @RequestMapping(value="/contact", method=RequestMethod.GET)
    public String contactPage(){
        return "contact";
    }


    @RequestMapping(value="/register", method=RequestMethod.GET)
    public ModelAndView registerPage(){
        ModelAndView mav=new ModelAndView();
        UserForm tmp=new UserForm();
        tmp.setName("Amol");
        tmp.setAbout("Amol Kailas Garje is my Friend !!!");
        tmp.setEmail("amol@gmail.com");
        tmp.setPassword("amol");
        tmp.setPhone("8623809917");
        mav.addObject("userdata", tmp);
        mav.setViewName("register");
        return mav;
    }


    @RequestMapping(value = "/login",method =RequestMethod.GET)
    public String loginPage(Model m){
        return new String("login");
    }

    // @RequestMapping(value = "/login",method =RequestMethod.POST)
    // public String loginPag(Model m){
    //     return "redirect:/loginq";
    // }

    // Data Fetch From the FrontEnd ..........@GetMapping("path")
    // @ModelAttribute("userdata") :--> All Error and Actual Informationis Come inside userdata if Error is Occure then we Need to Write it Else it Will not Compalsory 
    @PostMapping("/from_data")
    public String getFormData(@Valid @ModelAttribute("userdata") UserForm usrf,BindingResult rBindingResult,HttpSession session,Model m) {  // Jevha aaplyala url attribute he direct Object madhe jrr Bind Kryche aastil trr ModelAttribute cha wapr hoto

        if(rBindingResult.hasErrors()){
            m.addAttribute("userdata", usrf);
            return "register";
        }

        User usr=new User();

        usr.setName(usrf.getName());
        usr.setEmail(usrf.getEmail());
        usr.setAbout(usrf.getAbout());
        usr.setPassword(encode.encode(usrf.getPassword()));
        usr.setPhone(usrf.getPhone());
        usr.setProfilPic("https://d22e6o9mp4t2lx.cloudfront.net/cms/pfp1_fe1e0a17e8.jpg");

        userRoles rsl=new userRoles();
        rsl.setRole("USER");
        rsl.setUsr(usr);
        List<userRoles> tmp=List.of(rsl);
        usr.setRoles(tmp);
        service.saveUser(usr);

        Message message=Message.builder().content("Registration Successfull").type(MessageType.green).build();   // If You Want TO Send Message We Basically use this this is a User Manually Cretaed Message Format here Message is a Class

        session.setAttribute("message",message);
        
        // https://d22e6o9mp4t2lx.cloudfront.net/cms/pfp1_fe1e0a17e8.jpg

          return "redirect:/register";
    }
    
    


}
