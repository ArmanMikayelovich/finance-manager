package com.energizeglobal.internship.service;

import com.energizeglobal.internship.dao.interfaces.ExpenseDao;
import com.energizeglobal.internship.dao.interfaces.IncomeDao;
import com.energizeglobal.internship.dao.interfaces.UserDao;
import com.energizeglobal.internship.dto.ExpenseDto;
import com.energizeglobal.internship.dto.IncomeDto;
import com.energizeglobal.internship.dto.UserDto;
import com.energizeglobal.internship.entity.ExpenseEntity;
import com.energizeglobal.internship.entity.IncomeEntity;
import com.energizeglobal.internship.entity.UserEntity;
import com.energizeglobal.internship.service.interfaces.ExpenseService;
import com.energizeglobal.internship.service.interfaces.IncomeService;
import com.energizeglobal.internship.service.interfaces.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final UserDao userDao;
    private final ExpenseDao expenseDao;
    private final IncomeDao incomeDao;
    private final ExpenseService expenseService;
    private final IncomeService incomeService;

    public UserServiceImpl(UserDao userDao, ExpenseDao expenseDao, IncomeDao incomeDao,
                           ExpenseService expenseService, IncomeService incomeService) {
        this.userDao = userDao;
        this.expenseDao = expenseDao;
        this.incomeDao = incomeDao;
        this.expenseService = expenseService;
        this.incomeService = incomeService;
    }

    @Override
    public UserDto toDto(UserEntity userEntity) {
        UserDto dto = new UserDto();
        dto.setId(userEntity.getId());
        dto.setEmail(userEntity.getEmail());
        dto.setFirstName(userEntity.getFirstName());
        dto.setLastName(userEntity.getLastName());
        return dto;
    }

    @Override
    public UserDto save(UserDto user) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setEmail(user.getEmail());
        String encoded = encoder.encode(user.getPassword());
        entity.setPassword(encoded);
        userDao.save(entity);
        return toDto(entity);
    }

    @Override
    public void delete(UserDto user) {
        userDao.delete(user.getId());
    }

    @Override
    public UserDto update(UserDto user) {
        Optional<UserEntity> optional = userDao.find(user.getId());
        if (optional.isPresent()) {
            UserEntity entity = optional.get();
            entity.setFirstName(user.getFirstName());
            entity.setLastName(user.getLastName());
            entity.setPassword(user.getPassword());
            userDao.update(entity);
            return toDto(entity);
        }
        return null;
    }

    @Override
    public Optional<UserDto> find(Long id) {
        Optional<UserEntity> userEntity = userDao.find(id);
        if (userEntity.isPresent()) {
            UserEntity entity = userEntity.get();
            UserDto userDto = toDto(entity);
            return Optional.of(userDto);
        }
        return Optional.empty();
    }

    @Override
    public List<ExpenseDto> getAllCosts(UserDto userDto) {
        Optional<UserEntity> optionalUserEntity = userDao.find(userDto.getId());
        if (optionalUserEntity.isPresent()) {
            UserEntity entity = optionalUserEntity.get();
            List<ExpenseEntity> allCosts = userDao.getAllCosts(entity);
            return allCosts.stream().map(expenseService::toDto).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<IncomeDto> getAllIncomes(UserDto userDto) {
        Optional<UserEntity> optionalUserEntity = userDao.find(userDto.getId());
        if (optionalUserEntity.isPresent()) {
            UserEntity entity = optionalUserEntity.get();
            List<IncomeEntity> allCosts = userDao.getAllIncomes(entity);
            return allCosts.stream().map(incomeService::toDto).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> all = userDao.findAll();
        return all.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void addExpense(UserDto user, ExpenseDto expense) {
        Optional<UserEntity> optional = userDao.find(user.getId());
        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            ExpenseEntity expenseEntity = expenseService.toEntity(expense);
            userDao.addExpense(userEntity, expenseEntity);
        }
    }

    @Override
    public void addIncome(UserDto user, IncomeDto income) {
        Optional<UserEntity> optional = userDao.find(user.getId());
        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            IncomeEntity incomeEntity = incomeService.toEntity(income);
            userDao.addIncome(userEntity, incomeEntity);
        }
    }

    public List<IncomeDto> getPastIncomes(UserDto user) {
        Optional<UserEntity> optional = userDao.find(user.getId());

        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            List<IncomeEntity> pastIncomes = userDao.getPastIncomes(userEntity);
            return pastIncomes.stream().map(incomeService::toDto).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ExpenseDto> getPastCosts(UserDto user) {
        Optional<UserEntity> optional = userDao.find(user.getId());

        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            List<ExpenseEntity> pastIncomes = userDao.getPastCosts(userEntity);
            return pastIncomes.stream().map(expenseService::toDto).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<IncomeDto> getFutureIncomes(UserDto user) {
        Optional<UserEntity> optional = userDao.find(user.getId());

        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            List<IncomeEntity> futureIncomes = userDao.getFutureIncomes(userEntity);
            return futureIncomes.stream().map(incomeService::toDto).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public List<ExpenseDto> getFutureCosts(UserDto user) {
        Optional<UserEntity> optional = userDao.find(user.getId());

        if (optional.isPresent()) {
            UserEntity userEntity = optional.get();
            List<ExpenseEntity> futureCosts = userDao.getFutureCosts(userEntity);
            return futureCosts.stream().map(expenseService::toDto).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    @Override
    public Double getPastIncomesAmount(UserDto user) {
        List<IncomeDto> pastIncomes = getPastIncomes(user);
        return pastIncomes.stream().mapToDouble(IncomeDto::getAmount).reduce(Double::sum).orElse(0);
    }

    @Override
    public Double getPastCostsAmount(UserDto user) {
        List<ExpenseDto> pastCosts = getPastCosts(user);
        return pastCosts.stream().mapToDouble(ExpenseDto::getAmount).reduce(Double::sum).orElse(0);
    }

    @Override
    public Double getFutureIncomesAmount(UserDto user) {
        List<IncomeDto> futureIncomes = getFutureIncomes(user);
        return futureIncomes.stream().mapToDouble(IncomeDto::getAmount).reduce(Double::sum).orElse(0);
    }

    @Override
    public Double getFutureCostsAmount(UserDto user) {
        List<ExpenseDto> futureCosts = getFutureCosts(user);
        return futureCosts.stream().mapToDouble(ExpenseDto::getAmount).reduce(Double::sum).orElse(0);
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        Optional<UserEntity> entityOptional = userDao.findByEmail(email);
        if (entityOptional.isPresent()) {
            return entityOptional.get();
        }
        throw new RuntimeException("No user with email: " + email);
    }

    @Override
    public UserDto getAuthenticatedUser() {
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = principal.getUsername();
        UserEntity userByEmail = findUserByEmail(email);
        UserDto userDto = toDto(userByEmail);
        return userDto;
    }
}
