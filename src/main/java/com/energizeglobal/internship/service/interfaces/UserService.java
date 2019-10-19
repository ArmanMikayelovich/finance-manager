package com.energizeglobal.internship.service.interfaces;

import com.energizeglobal.internship.dto.ExpenseDto;
import com.energizeglobal.internship.dto.IncomeDto;
import com.energizeglobal.internship.dto.UserDto;
import com.energizeglobal.internship.entity.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

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

    void addExpense(UserDto user, ExpenseDto expense);

    void addIncome(UserDto user, IncomeDto income);

    List<IncomeDto> getPastIncomes(UserDto user);

    List<ExpenseDto> getPastCosts(UserDto user);

    List<IncomeDto> getFutureIncomes(UserDto user);

    List<ExpenseDto> getFutureCosts(UserDto user);

    Double getPastIncomesAmount(UserDto user);

    Double getPastCostsAmount(UserDto user);

    Double getFutureIncomesAmount(UserDto user);

    Double getFutureCostsAmount(UserDto user);

    UserEntity findUserByEmail(String email);

    UserDto getAuthenticatedUser();

}
