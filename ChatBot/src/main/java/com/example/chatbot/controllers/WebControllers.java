package com.example.chatbot.controllers;

import com.example.chatbot.entities.User;
import com.example.chatbot.utils.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class WebControllers {

    @Autowired
    CommonService commonService;

    BCryptPasswordEncoder bCryptPasswordEncoder =  new BCryptPasswordEncoder();

    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String index(){

        Authentication authetication = SecurityContextHolder.getContext().getAuthentication();
        if(authetication != null && authetication.getPrincipal() instanceof UserDetails && ((UserDetails)authetication.getPrincipal()).getAuthorities().stream().anyMatch( a -> a.getAuthority().equals("ADMIN"))){
            return "redirect:/web/admin";
        }
        return "index";
    }


    @RequestMapping(value = "/web/login" , method = RequestMethod.GET)
    public String login(){
        return "LoginPage";
    }


    @RequestMapping(value = "/web/register" , method = RequestMethod.GET)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/web/admin" , method = RequestMethod.GET)
    public String admin(){
        return "admin";
    }

    @RequestMapping(value = "/web/register" , method = RequestMethod.POST)
    public String save( User user ){
        user.setPassword( bCryptPasswordEncoder.encode( user.getPassword() ));
        commonService.saveUser( user );/////  ==== 5
        return "redirect:/web/login";
    }
}
