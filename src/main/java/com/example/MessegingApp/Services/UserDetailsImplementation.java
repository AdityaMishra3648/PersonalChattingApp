package com.example.MessegingApp.Services;

import com.example.MessegingApp.Entity.User;
import com.example.MessegingApp.Repositary.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsImplementation implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUserName(username);
        if(user!=null){
            return org.springframework.security.core.userdetails.User.builder().username(user.getUserName()).password(user.getPassword()).roles("USER").build();
        }
        throw new UsernameNotFoundException("Usern not found with username "+username);
    }

}
