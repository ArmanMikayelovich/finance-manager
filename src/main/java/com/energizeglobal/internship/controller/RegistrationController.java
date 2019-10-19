package com.energizeglobal.internship.controller;

import com.energizeglobal.internship.dto.UserDto;
import com.energizeglobal.internship.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getRegistrationPage() {
        ModelAndView model = new ModelAndView("registration");
        return model;
    }

    @PostMapping
    public void doRegister(UserDto user, HttpServletResponse response) throws IOException {
        userService.save(user);
        response.sendRedirect("/login");
    }
}
