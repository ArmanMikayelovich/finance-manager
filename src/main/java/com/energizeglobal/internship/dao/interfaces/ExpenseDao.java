package com.energizeglobal.internship.dao.interfaces;

import com.energizeglobal.internship.dto.ExpenseDto;
import com.energizeglobal.internship.entity.ExpenseEntity;

import java.util.List;
import java.util.Optional;

public interface ExpenseDao {
    ExpenseEntity save(ExpenseEntity entity);

    ExpenseEntity update(ExpenseEntity entity);

    Optional<ExpenseEntity> find(Long id);

    List<ExpenseEntity> findAll();

    void delete(ExpenseEntity entity);

    void delete(Long id);
}
