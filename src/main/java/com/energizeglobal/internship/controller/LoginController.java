package com.energizeglobal.internship.controller;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
    public ModelAndView getLoginPage() {
        ModelAndView model = new ModelAndView("login");
        return model;
    }
}
