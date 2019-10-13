package com.energizeglobal.internship.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
@Component
public class PersistenceUtil {

    @Bean(name = "entityManagerFactory")
    @Scope(scopeName = "singleton")
    private  EntityManagerFactory createEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("finance");
    }

}
