package com.energizeglobal.internship.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class IncomeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @ManyToOne
    private UserEntity user;

    private String description;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private Date date;


}
