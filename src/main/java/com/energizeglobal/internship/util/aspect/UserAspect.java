package com.energizeglobal.internship.util.aspect;

import com.energizeglobal.internship.dto.UserDto;
import com.energizeglobal.internship.entity.UserEntity;
import com.energizeglobal.internship.service.interfaces.UserService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {
    private final UserService userService;

    public UserAspect(UserService userService) {
        this.userService = userService;
    }

    @Pointcut("execution(public * com.energizeglobal.internship.controller.UserController.*(..)")
    public void callAspect() {
    }

    @Before("callAspect()")
    public void setUser(ProceedingJoinPoint jp) throws Throwable {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.getUsername();
        UserEntity userByEmail = userService.findUserByEmail(email);
        UserDto userDto = userService.toDto(userByEmail);
        jp.proceed(new Object[]{userDto});
        System.out.println("user" + userDto + " called");
    }
}
