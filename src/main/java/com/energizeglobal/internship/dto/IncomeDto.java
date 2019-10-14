package com.energizeglobal.internship.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class IncomeDto {
    private Long id;

    private Double amount;

    private String description;

    private LocalDate dateTime;

}
