package com.energizeglobal.internship.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExpenseDto {
    private Long id;

    private Double amount;

    private String description;

    private LocalDateTime dateTime;

}
