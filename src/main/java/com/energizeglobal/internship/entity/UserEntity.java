package com.energizeglobal.internship.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(indexes = {@Index(name = "email_index", columnList = "email", unique = true)})
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    private String firstName;

    private String lastName;
    @Column(unique = true, updatable = false)

    private String email;

    private String password;


    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "user_id")
    private List<ExpenseEntity> costs = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    @JoinColumn(name = "user_id")
    private List<IncomeEntity> incomes = new ArrayList<>();

    private String role = "user";
}
