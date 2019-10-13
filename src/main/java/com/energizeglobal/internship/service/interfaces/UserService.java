package com.energizeglobal.internship.service.interfaces;

import com.energizeglobal.internship.dto.ExpenseDto;
import com.energizeglobal.internship.dto.IncomeDto;
import com.energizeglobal.internship.dto.UserDto;
import com.energizeglobal.internship.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

     UserDto toDto(UserEntity userEntity);

    UserDto save(UserDto user);

     void delete(UserDto user);

    UserDto update(UserDto userDto);

     Optional<UserDto> find(Long id);

     List<ExpenseDto> getAllCosts(UserDto userDto);

     List<IncomeDto> getAllIncomes(UserDto userDto);

    List<UserDto> getAllUsers();

     void addExpense(UserDto user,ExpenseDto expense);

     void addIncome(UserDto user,IncomeDto income);

}
