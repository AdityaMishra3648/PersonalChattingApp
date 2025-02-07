package com.example.MessegingApp.Services;

import com.example.MessegingApp.Entity.User;
import com.example.MessegingApp.Repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserService {

    @Autowired
    private UserRepo ur;

    public boolean saveEntry(User u){
        if(u.getPassword().length()==0 || u.getUserName().length()==0)return false;
        if(ur.findByUserName(u.getUserName())==null)return false;
        ur.save(u);
        return true;

    }


}
