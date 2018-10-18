/*
package com.springbootstarter.controller;

import com.springbootstarter.bean.Users;
import com.springbootstarter.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
//@SessionAttributes("username")
public class LoginController {

    @Autowired
    LoginService service;
   */
/* @RequestMapping(value="/login", method = RequestMethod.GET)
    public String showLoginPage(ModelMap model){
        return "login";
    }*//*

   */
/* @RequestMapping("/")
    public ModelAndView viewHome() {
        return new ModelAndView( "login");
    }*//*

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView showWelcomePageP(ModelMap model, @RequestParam String username, @RequestParam String password){
        System.out.println("post ");
        Users user = service.validateUser(username);
        System.out.println("post ");
        if (user !=null && user.getPassword()==password) {
           // model.put("name", name);
            System.out.println("valid user "+username);
            if(user.getRole()=="admin")
                return new ModelAndView("AdminPage");
            else
                return new ModelAndView("UserPage");
        }else{
            System.out.println(" user not found"+username+password);
            model.put("errorMessage", "Invalid Credentials");
            return new ModelAndView("login");
        }

    }

}
*/
