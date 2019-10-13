package com.energizeglobal.internship.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManagerFactory;

@Controller
public class HomeController {
    @Autowired
    private ApplicationContext context;

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public ModelAndView getHomePage(/**HttpServletRequest request, HttpServletResponse response*/) {
        ModelAndView modelAndView = new ModelAndView("home");
        EntityManagerFactory factory = (EntityManagerFactory) context.getBean(EntityManagerFactory.class);
        return modelAndView;
    }
}
