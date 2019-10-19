package com.energizeglobal.internship.service;

import com.energizeglobal.internship.entity.UserEntity;
import com.energizeglobal.internship.service.interfaces.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userJpaService;

    public UserDetailsServiceImpl(UserService userJpaService) {
        this.userJpaService = userJpaService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userByEmail = userJpaService.findUserByEmail(email);
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userByEmail.getUserRole().name());
        Set<GrantedAuthority> roleSet =Collections.singleton(authority);
        return new User(userByEmail.getEmail(), userByEmail.getPassword(), roleSet);
    }
}
