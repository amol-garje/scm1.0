package com.example.demo.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import java.util.*;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Contact {

      @Id
      private String id;
      private String name;
      private String email;
      private String phoneNumber;
      private String address;
      private String picture;
      private String description;
      private boolean favorite=false;

       @OneToMany(mappedBy = "contact",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
       private List<SocialLink> contacts=new ArrayList<SocialLink>();


      @ManyToOne
      private User user;


}
      