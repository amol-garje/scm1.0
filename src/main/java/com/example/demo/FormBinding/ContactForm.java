package com.example.demo.FormBinding;

import org.springframework.web.multipart.MultipartFile;

import com.example.demo.validaters.validFile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class ContactForm {

    @NotBlank(message="Name id Required")
    private String name;
    
    @Email(message = "Invalid Email Address")
    @NotBlank(message = "Email is Not Blank")
    private String email;

    @NotBlank(message = "Phone Number is Required")
    @Pattern(regexp = "^[0-9]{10}$",message = "Invalid Phone Number")
    private String phoneNumber;

    @NotBlank(message = "Address is Required")
    private String address;

    @validFile
    private MultipartFile picture;

    private String description;
    private boolean favorite;
    private String websiteLink;
    private String linkedinLink;


    private String picture1;  // url set



    
 


    public ContactForm() {

    }

   

    public ContactForm(@NotBlank(message = "Name id Required") String name,
            @Email(message = "Invalid Email Address") @NotBlank(message = "Email is Not Blank") String email,
            @NotBlank(message = "Phone Number is Required") @Pattern(regexp = "^[0-9]{10}$", message = "Invalid Phone Number") String phoneNumber,
            @NotBlank(message = "Address is Required") String address, MultipartFile picture, String description,
            boolean favorite, String websiteLink, String linkedinLink, String picture1) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.picture = picture;
        this.description = description;
        this.favorite = favorite;
        this.websiteLink = websiteLink;
        this.linkedinLink = linkedinLink;
        this.picture1 = picture1;
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

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
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

    @Override
    public String toString() {
        return "ContactForm [name=" + name + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address="
                + address + ", picture=" + picture + ", description=" + description + ", favorite=" + favorite
                + ", websiteLink=" + websiteLink + ", linkedinLink=" + linkedinLink + "]";
    }

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }
}
