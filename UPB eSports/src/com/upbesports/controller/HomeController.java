package com.upbesports.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class HomeController
{
    @RequestMapping("/")
    @ResponseBody
    public final String home() 
    {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return username.equals("anonymousUser") ? "Anonymous User" : username;
    }
}
