package com.example.demo.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
      private String websiteLink;
      private String linkedinLink;
      private String cloudinaryImagePublicid;

       @OneToMany(mappedBy = "contact",fetch = FetchType.LAZY,cascade = CascadeType.ALL) 
       private List<SocialLink> contacts=new ArrayList<SocialLink>();

       
       

      

      public Contact(String id, String name, String email, String phoneNumber, String address, String picture,
                  String description, boolean favorite, String websiteLink, String linkedinLink,
                  String cloudinaryImagePublicid, List<SocialLink> contacts, User user) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.picture = picture;
            this.description = description;
            this.favorite = favorite;
            this.websiteLink = websiteLink;
            this.linkedinLink = linkedinLink;
            this.cloudinaryImagePublicid = cloudinaryImagePublicid;
            this.contacts = contacts;
            this.user = user;
      }


      


      public String getWebsiteLink() {
            return websiteLink;
      }


      public void setWebsiteLink(String websiteLink) {
            this.websiteLink = websiteLink;
      }


      public String getLinkedinLink() {
            return linkedinLink;
      }


      public void setLinkedinLink(String linkedinLink) {
            this.linkedinLink = linkedinLink;
      }


      public Contact() {
      }

      @ManyToOne
      @JoinColumn(name = "user_id")
      @JsonIgnore
      private User user;


      public String getId() {
            return id;
      }


      public void setId(String id) {
            this.id = id;
      }


      public String getName() {
            return name;
      }


      public void setName(String name) {
            this.name = name;
      }


      public String getEmail() {
            return email;
      }


      public void setEmail(String email) {
            this.email = email;
      }


      public String getPhoneNumber() {
            return phoneNumber;
      }


      public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
      }


      public String getAddress() {
            return address;
      }


      public void setAddress(String address) {
            this.address = address;
      }


      public String getPicture() {
            return picture;
      }


      public void setPicture(String picture) {
            this.picture = picture;
      }


      public String getDescription() {
            return description;
      }


      public void setDescription(String description) {
            this.description = description;
      }


      public boolean isFavorite() {
            return favorite;
      }


      public void setFavorite(boolean favorite) {
            this.favorite = favorite;
      }


      public List<SocialLink> getContacts() {
            return contacts;
      }


      public void setContacts(List<SocialLink> contacts) {
            this.contacts = contacts;
      }


      public User getUser() {
            return user;
      }


      public void setUser(User user) {
            this.user = user;
      }

      @Override
      public String toString() {
            return "Contact [id=" + id + ", name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber
                        + ", address=" + address + ", picture=" + picture + ", description=" + description
                        + ", favorite=" + favorite + ", contacts=" + contacts + ", user=" + user + "]";
      }


      public String getCloudinaryImagePublicid() {
            return cloudinaryImagePublicid;
      }


      public void setCloudinaryImagePublicid(String cloudinaryImagePublicid) {
            this.cloudinaryImagePublicid = cloudinaryImagePublicid;
      }

}
      