package com.example.demo.BindingData;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserForm {
    @NotBlank(message = "user name is required")
    @Size(min = 3, message = "minimum 3 character required")
    private String name;

    @Email(message = "Invalid Email Address")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "minimum three character required")
    private String password;

    @NotBlank(message = "About is required")
    private String about;

    @Size(min = 8, max = 12, message = "Invalid Phone Number")
    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserForm(String name, String email, String password, String about, String phone) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.about = about;
        this.phone = phone;
    }

    public UserForm() {
    }

    @Override
    public String toString() {
        return "UserForm [name=" + name + ", email=" + email + ", password=" + password + ", about=" + about
                + ", phone=" + phone + "]";
    }

}
