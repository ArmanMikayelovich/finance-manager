package com.energizeglobal.internship.service.interfaces;

import com.energizeglobal.internship.dto.ExpenseDto;
import com.energizeglobal.internship.entity.ExpenseEntity;

public interface ExpenseService {
    ExpenseDto toDto(ExpenseEntity ExpenseEntity);

    ExpenseEntity toEntity(ExpenseDto dto);

    void update(ExpenseDto dto);

    void delete(ExpenseDto expenseDto);

    ExpenseDto find(Long id);

}
