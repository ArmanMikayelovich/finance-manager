package com.energizeglobal.internship.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDto {
    private Long id;

    private Double amount;

    private String description;

    private LocalDate dateTime;

}
