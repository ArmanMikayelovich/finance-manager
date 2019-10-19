package com.energizeglobal.internship.controller;

import com.energizeglobal.internship.dto.UserDto;
import com.energizeglobal.internship.service.interfaces.ExpenseService;
import com.energizeglobal.internship.service.interfaces.IncomeService;
import com.energizeglobal.internship.service.interfaces.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    private final UserService userService;
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    public UserController(UserService userService, ExpenseService expenseService,
                          IncomeService incomeService) {
        this.userService = userService;
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }


    @RequestMapping(value = {"/userPage", "/"}, method = RequestMethod.GET)
    public ModelAndView getUserPage() {
        UserDto user = userService.getAuthenticatedUser();
        ModelAndView model = new ModelAndView("mainUserPage");
        model.addObject("user", user);
        model.addObject("pastIncomesAmount", userService.getPastIncomesAmount(user));
        model.addObject("pastCostsAmount", userService.getPastCostsAmount(user));
        model.addObject("futureIncomesAmount", userService.getFutureIncomesAmount(user));
        model.addObject("futureCostsAmount", userService.getFutureCostsAmount(user));
        return model;
    }

    @GetMapping(value = "/pastOps")
    public ModelAndView getPastOpsOfUser() {
        UserDto user = userService.getAuthenticatedUser();
        ModelAndView model = new ModelAndView("pastOperations");
        model.addObject("user", user);
        model.addObject("pastCosts", userService.getPastCosts(user));
        model.addObject("pastIncomes", userService.getPastIncomes(user));
        return model;
    }

    @GetMapping(value = "/futureOps")
    public ModelAndView getFutureOpsOfUser() {
        UserDto user = userService.getAuthenticatedUser();
        ModelAndView model = new ModelAndView("futureOperations");
        model.addObject("user", user);
        model.addObject("futureCosts", userService.getFutureCosts(user));
        model.addObject("futureIncomes", userService.getFutureIncomes(user));
        return model;
    }
}
