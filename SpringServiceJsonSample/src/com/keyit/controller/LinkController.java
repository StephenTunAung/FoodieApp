package com.keyit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LinkController {
     
    @RequestMapping(value="/")
    public ModelAndView mainPage() {
        return new ModelAndView("redirect:/restaurant/showLoginPage");
    }
     
    @RequestMapping(value="/index")
    public ModelAndView indexPage() {
        return new ModelAndView("redirect:/restaurant/showLoginPage");
    }
    
    @RequestMapping(value="/login")
    public ModelAndView login() {
        return new ModelAndView("redirect:/restaurant/showLoginPage");
    }
 
}