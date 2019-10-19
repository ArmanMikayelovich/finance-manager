package com.energizeglobal.internship.dao.interfaces;

import com.energizeglobal.internship.dto.IncomeDto;
import com.energizeglobal.internship.entity.ExpenseEntity;
import com.energizeglobal.internship.entity.IncomeEntity;
import com.energizeglobal.internship.entity.UserEntity;

import java.util.List;
import java.util.Optional;


public interface UserDao {
    UserEntity save(UserEntity userEntity);

    UserEntity update(UserEntity userEntity);

    Optional<UserEntity> find(Long id);

    List<UserEntity> findAll();

    void delete(UserEntity userEntity);

    void delete(Long id);

    List<IncomeEntity> getAllIncomes(UserEntity user);

    List<ExpenseEntity> getAllCosts(UserEntity user);

    void addExpense(UserEntity user,ExpenseEntity expense);

    void addIncome(UserEntity user,IncomeEntity income);

    List<IncomeEntity> getPastIncomes(UserEntity user);

    List<ExpenseEntity> getPastCosts(UserEntity user);

    List<IncomeEntity> getFutureIncomes(UserEntity user);

    List<ExpenseEntity> getFutureCosts(UserEntity user);

    Optional<UserEntity> findByEmail(String email);
}
