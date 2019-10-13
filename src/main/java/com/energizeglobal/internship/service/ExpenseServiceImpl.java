package com.energizeglobal.internship.service;

import com.energizeglobal.internship.dao.interfaces.ExpenseDao;
import com.energizeglobal.internship.dto.ExpenseDto;
import com.energizeglobal.internship.entity.ExpenseEntity;
import com.energizeglobal.internship.service.interfaces.ExpenseService;
import com.energizeglobal.internship.util.TimeUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    private final TimeUtil timeUtil;
    private final ExpenseDao expenseDao;

    public ExpenseServiceImpl(TimeUtil timeUtil, ExpenseDao expenseDao) {
        this.timeUtil = timeUtil;
        this.expenseDao = expenseDao;
    }

    @Override
    public ExpenseDto toDto(ExpenseEntity entity) {
        ExpenseDto dto = new ExpenseDto();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setDateTime(timeUtil.sqlDateToLocalDateTime(entity.getDate()));
        dto.setDescription(entity.getDescription());
        return dto;
    }

    @Override
    public void update(ExpenseDto dto) {
        Optional<ExpenseEntity> optionalIncomeEntity = expenseDao.find(dto.getId());
        if (optionalIncomeEntity.isPresent()) {
            ExpenseEntity entity = optionalIncomeEntity.get();
            entity.setDescription(dto.getDescription());
            entity.setAmount(dto.getAmount());
            expenseDao.update(entity);
        }
    }

    @Override
    public void delete(ExpenseDto dto) {
        Optional<ExpenseEntity> optionalIncomeEntity = expenseDao.find(dto.getId());
        optionalIncomeEntity.ifPresent(expenseDao::delete);
    }

    @Override
    public ExpenseDto find(Long id) {
        return expenseDao.find(id).map(this::toDto).orElse(null);
    }

    @Override
    public ExpenseEntity toEntity(ExpenseDto dto) {
        ExpenseEntity expenseEntity = new ExpenseEntity();
        expenseEntity.setAmount(dto.getAmount());
        expenseEntity.setDescription(dto.getDescription());
        return expenseEntity;
    }
}
