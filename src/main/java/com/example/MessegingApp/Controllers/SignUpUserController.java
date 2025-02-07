package com.example.MessegingApp.Controllers;

import com.example.MessegingApp.Entity.User;
import com.example.MessegingApp.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signUp")
public class SignUpUserController {
    @Autowired
    private UserService us;

    @PostMapping
    public String saveUser(@RequestBody User u){
        System.out.println("username is "+u.getUserName()+" password is "+u.getPassword());
        boolean res = us.saveEntry(u);
        if(res)return "User created successfully";
        else return "username already exists or password is incorrect";
    }

}
