package com.energizeglobal.internship.controller;

import com.energizeglobal.internship.dto.UserDto;
import com.energizeglobal.internship.service.interfaces.ExpenseService;
import com.energizeglobal.internship.service.interfaces.IncomeService;
import com.energizeglobal.internship.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class HomeController {

    private final UserService userService;
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    public HomeController(UserService userService, ExpenseService expenseService,
                          IncomeService incomeService) {
        this.userService = userService;
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getHomePage() {
        ModelAndView model = new ModelAndView("mainUserPage");
        Optional<UserDto> userOptional = userService.find(1L);
        model.addObject("user", userOptional.get());
        model.addObject("pastIncomes", userService.getPastIncomes(userService.find(1L).get()));
        model.addObject("pastCosts", userService.getPastCosts(userService.find(1L).get()));
        return model;
    }

}
