package com.example.demo.controller;

import java.util.List;
import java.net.Authenticator;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Contact;
import com.example.demo.Entity.User;
import com.example.demo.FormBinding.ContactForm;
import com.example.demo.Services.ImageService;
import com.example.demo.Services.UserService;
import com.example.demo.Services.contactService;
import com.example.demo.Services.Impl.contactServiceImpleme;
import com.example.demo.helper.Helper;
import com.example.demo.helper.Message;
import com.example.demo.helper.MessageType;
import com.example.demo.repository.contactReposi;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contact")
public class contactController {


    @Autowired
    contactServiceImpleme servi;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;


    @Autowired
    private contactReposi reposi;


    Logger logger=LoggerFactory.getLogger(contactController.class);

    @RequestMapping("/add")
    public String addContact(Model m){

        ContactForm conForm=new ContactForm();
        m.addAttribute("contactForm", conForm);

        return "user/add_contact";
    }


    @RequestMapping(value="/add",method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm conf,BindingResult result,HttpSession session ,Authentication authentication){


        if(result.hasErrors()){
                  
                session.setAttribute("message",Message.builder().content("please correct the errors").type(MessageType.red).build());

                return "/user/add_contact";
        }

        logger.info("file Information :{}",conf.getPicture().getOriginalFilename());

        String username=Helper.getEmailOfaLogedInUser(authentication);
        User usr=userService.getUserByEmail(username);

        String fileName=UUID.randomUUID().toString();

        String filrURL=imageService.uploadImage(conf.getPicture(),fileName);

        Contact cont=new Contact();
        cont.setName(conf.getName());
        cont.setAddress(conf.getAddress());
        cont.setEmail(conf.getEmail());
        cont.setFavorite(conf.getFavorite());
        cont.setPhoneNumber(conf.getPhoneNumber());
        cont.setDescription(conf.getDescription());
        cont.setLinkedinLink(conf.getLinkedinLink());
        cont.setWebsiteLink(conf.getWebsiteLink());
        cont.setPicture(filrURL);
        cont.setUser(usr);
        cont.setCloudinaryImagePublicid(fileName);
        System.out.println(conf);
        servi.save(cont);
        session.setAttribute("message",Message.builder().content("Number added successfuly").type(MessageType.green).build());
        return "redirect:/user/contact/add";
    }

    @RequestMapping
    public String viewContact(
    
    @RequestParam(value="page",defaultValue ="0") int page,
    @RequestParam(value="size",defaultValue = "6") int size,
    @RequestParam(value = "sortby",defaultValue = "name") String sortBy,
    @RequestParam(value="direction",defaultValue = "asc") String direction
    ,Authentication authentication,Model modl){

         String userName=Helper.getEmailOfaLogedInUser(authentication);
         User usr=userService.getUserByEmail(userName);

         Page<Contact> pageContact=servi.getByUser(usr, page, size, sortBy, direction);
         

         modl.addAttribute("pagecontact", pageContact);
          return "user/contact";
    }

    @RequestMapping("/search")
    public String searchHandler(
        @RequestParam(value = "field") String field,
        @RequestParam(value="keyword") String value,
        @RequestParam(value="page",defaultValue ="0") int page,
        @RequestParam(value="size",defaultValue = "6") int size,
        @RequestParam(value = "sortby",defaultValue = "name") String sortBy,
        @RequestParam(value="direction",defaultValue = "asc") String direction,
        Authentication authentication,
        Model model
    ){

        System.out.println(field+" :--->  "+value);

        String userName=Helper.getEmailOfaLogedInUser(authentication);
        User usr=userService.getUserByEmail(userName);




         Page<Contact> pagecontact=null;
         if(field.equalsIgnoreCase("name")){
            pagecontact= servi.searchByName(value, size, page, sortBy, direction,usr);
         }else  if(field.equalsIgnoreCase("email")){
            pagecontact= servi.searchByEmail(value, size, page, sortBy, direction,usr);
         }else if(field.equalsIgnoreCase("phone")){
            pagecontact= servi.searchByPhoneNumber(value, size, page, sortBy, direction,usr);
         }



        model.addAttribute("pagecontact", pagecontact);
        return "user/search";
    }






    @RequestMapping(value = "/delete/{id}")
    public String deleteContact(@PathVariable String id){
           System.out.println("Your data is Deleteed Sucessfully "+id);
           reposi.deleteById(id);

            return "redirect:/user/contact";
    }



    @GetMapping("/view/{id}")
    public String updateContactFormView(@PathVariable String id,Model model){
           Contact con=servi.getById(id);


           ContactForm conform=new ContactForm();
           conform.setName(con.getName());
           conform.setAddress(con.getAddress());
           conform.setDescription(con.getDescription());
           conform.setEmail(con.getEmail());
           conform.setPhoneNumber(con.getPhoneNumber());
           conform.setFavorite(con.isFavorite());
           conform.setWebsiteLink(con.getWebsiteLink());
           conform.setLinkedinLink(con.getLinkedinLink());
           conform.setPicture1(con.getPicture());


           model.addAttribute("contactForm", conform);
           model.addAttribute("contactId",id);

           return "user/update_contact_view";
    }



    @RequestMapping(value = "/update/{contactId}",method = RequestMethod.POST)
    public String updateContact(@PathVariable("contactId") String contactId,@ModelAttribute ContactForm contactForm,Model model){
        

        Contact cont=servi.getById(contactId);
        cont.setId(contactId);
        cont.setName(contactForm.getName());
        cont.setAddress(contactForm.getAddress());
        cont.setEmail(contactForm.getEmail());
        cont.setPhoneNumber(contactForm.getPhoneNumber());
        cont.setDescription(contactForm.getDescription());
        cont.setFavorite(contactForm.getFavorite());
        cont.setWebsiteLink(contactForm.getWebsiteLink());
        cont.setLinkedinLink(contactForm.getLinkedinLink());
        cont.setPicture(contactForm.getPicture1());
      
       if(contactForm.getPicture()!=null && !contactForm.getPicture().isEmpty()){
        String fileName=UUID.randomUUID().toString();
        String imageUrl=imageService.uploadImage(contactForm.getPicture(),fileName);
        cont.setCloudinaryImagePublicid(fileName);
        cont.setPicture(imageUrl);
        
       }else{
        
       }

        Contact contu=servi.update(cont);
        model.addAttribute("message",Message.builder().content("Updated suceessfuly").type(MessageType.green).build());
        return "redirect:/user/contact/view/"+contactId;

    }





}
