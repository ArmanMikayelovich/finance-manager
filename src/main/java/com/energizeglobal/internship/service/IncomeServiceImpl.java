package com.energizeglobal.internship.service;

import com.energizeglobal.internship.dao.interfaces.IncomeDao;
import com.energizeglobal.internship.dto.IncomeDto;
import com.energizeglobal.internship.entity.IncomeEntity;
import com.energizeglobal.internship.service.interfaces.IncomeService;
import com.energizeglobal.internship.util.TimeUtil;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class IncomeServiceImpl implements IncomeService {
    private final IncomeDao incomeDao;
    private final TimeUtil timeUtil;

    public IncomeServiceImpl(IncomeDao incomeDao, TimeUtil timeUtil) {
        this.incomeDao = incomeDao;
        this.timeUtil = timeUtil;
    }

    @Override
    public IncomeDto toDto(IncomeEntity entity) {
        IncomeDto dto = new IncomeDto();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setDateTime(timeUtil.sqlDateToLocalDateTime(entity.getDate()));
        dto.setDescription(entity.getDescription());
        return dto;
    }



    @Override
    public void update(IncomeDto dto) {
        Optional<IncomeEntity> optionalIncomeEntity = incomeDao.find(dto.getId());
        if (optionalIncomeEntity.isPresent()) {
            IncomeEntity entity = optionalIncomeEntity.get();
            entity.setDescription(dto.getDescription());
            entity.setAmount(dto.getAmount());
            incomeDao.update(entity);
        }
    }

    @Override
    public void delete(IncomeDto dto) {
        Optional<IncomeEntity> optionalIncomeEntity = incomeDao.find(dto.getId());
        if (optionalIncomeEntity.isPresent()) {
            IncomeEntity entity = optionalIncomeEntity.get();
            incomeDao.delete(entity);
        }
    }

    @Override
    public IncomeDto find(Long id) {
        Optional<IncomeEntity> optionalIncomeEntity = incomeDao.find(id);
        return optionalIncomeEntity.map(this::toDto).orElse(null);
    }

    @Override
    public IncomeEntity toEntity(IncomeDto dto) {
        IncomeEntity incomeEntity = new IncomeEntity();
        incomeEntity.setDescription(dto.getDescription());
        incomeEntity.setAmount(dto.getAmount());
        return incomeEntity;
    }
}
