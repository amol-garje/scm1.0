package com.example.demo.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;

import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    

    public User() {
    
    }

    @Id
    private String userId;
    
    @Column(name="user_name",nullable = false)
    private String name;

    @Column(unique = true,nullable=false) 
    private String email;


    private String password;

    @Column(length = 1000)
    private String about;

    @Column(length = 1000)
    private String profilPic;
    private String phone;

    private boolean enable=true;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    // @Enumerated(value = EnumType.STRING)   // to set the Default value in the Database
    private String provider=Provider.SELF.toString();
    
    private String providerUserId;


    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    private List<Contact> contact=new ArrayList<Contact>(); // When we Assign The Value to it then it will consider as a Default Values


    @OneToMany(mappedBy = "usr",cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<userRoles> roles;



    public User(String userId, String name, String email, String password, String about, String profilPic,
            String phone, boolean enable, boolean emailVerified, boolean phoneVerified, String provider,
            String providerUserId, List<Contact> contact) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.profilPic = profilPic;
        this.phone = phone;
        this.enable = enable;
        this.emailVerified = emailVerified;
        this.phoneVerified = phoneVerified;
        this.provider = provider;
        this.providerUserId = providerUserId;
        this.contact = contact;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String userId) {
        this.userId = userId;
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


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public String getAbout() {
        return about;
    }


    public void setAbout(String about) {
        this.about = about;
    }


    public String getProfilPic() {
        return profilPic;
    }


    public void setProfilPic(String profilPic) {
        this.profilPic = profilPic;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public boolean isEnable() {
        return enable;
    }


    public void setEnable(boolean enable) {
        this.enable = enable;
    }


    public boolean isEmailVerified() {
        return emailVerified;
    }


    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }


    public boolean isPhoneVerified() {
        return phoneVerified;
    }


    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }


    public String getProvider() {
        return provider;
    }


    public void setProvider(String provider) {
        this.provider = provider;
    }


    public String getProviderUserId() {
        return providerUserId;
    }


    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }


    public List<Contact> getContact() {
        return contact;
    }


    public void setContact(List<Contact> contact) {
        this.contact = contact;
    }


    public List<userRoles> getRoles() {
        return roles;
    }


    public void setRoles(List<userRoles> roles) {
        this.roles = roles;
    }

    


    


    


}
