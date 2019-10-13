package com.energizeglobal.internship.dao.interfaces;

import com.energizeglobal.internship.entity.IncomeEntity;

import java.util.List;
import java.util.Optional;

public interface IncomeDao {
    IncomeEntity save(IncomeEntity entity);

    IncomeEntity update(IncomeEntity entity);

    Optional<IncomeEntity> find(Long id);

    List<IncomeEntity> findAll();

    void delete(IncomeEntity entity);

    void delete(Integer id);

}
