package com.energizeglobal.internship.service.interfaces;

import com.energizeglobal.internship.dto.IncomeDto;
import com.energizeglobal.internship.entity.IncomeEntity;

public interface IncomeService {
    IncomeDto toDto(IncomeEntity incomeEntity);

    IncomeEntity toEntity(IncomeDto dto);

    void update(IncomeDto incomeDto);

    void delete(IncomeDto incomeDto);

    IncomeDto find(Long id);

}
