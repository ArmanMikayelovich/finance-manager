package com.energizeglobal.internship;

import com.energizeglobal.internship.dto.ExpenseDto;
import com.energizeglobal.internship.dto.UserDto;
import com.energizeglobal.internship.service.interfaces.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class TestMainClass {


    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/testApplicationContext.xml");

        testAddIncome(context);
    }

    static void testAddIncome(ApplicationContext context) {
        EntityManagerFactory entityManagerFactory = context.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        UserDto user = getUser();
        ExpenseDto expense = getExpense();
        UserService userService = context.getBean(UserService.class);
        user = userService.save(user);
        System.out.println(user);
        userService.addExpense(user, expense);
        System.out.println(userService.getAllCosts(user));

    }

    static UserDto getUser() {
        UserDto user = new UserDto();
        user.setFirstName("Arman");
        user.setLastName("Arshakyan");
        user.setEmail("ArmanMikayelovich@gmail.com");
        user.setPassword("newPasswordForMe");
        return user;
    }

    static ExpenseDto getExpense() {
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setDescription("first expense");
        expenseDto.setAmount(1000000D);
        return expenseDto;
    }
}
