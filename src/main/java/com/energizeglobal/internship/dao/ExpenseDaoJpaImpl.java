package com.energizeglobal.internship.dao;

import com.energizeglobal.internship.dao.interfaces.ExpenseDao;
import com.energizeglobal.internship.dto.ExpenseDto;
import com.energizeglobal.internship.entity.ExpenseEntity;
import com.energizeglobal.internship.entity.IncomeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class ExpenseDaoJpaImpl implements ExpenseDao {
    private final EntityManagerFactory factory;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public ExpenseDaoJpaImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }



    @Override
    public ExpenseEntity save(ExpenseEntity entity) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public ExpenseEntity update(ExpenseEntity entity) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.persist(entity);
            em.getTransaction().commit();
            return entity;
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Optional<ExpenseEntity> find(Long id) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            ExpenseEntity ExpenseEntity = em.find(ExpenseEntity.class, id);
            em.getTransaction().commit();
            return Optional.of(ExpenseEntity);
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return Optional.empty();
    }

    @Override
    public List<ExpenseEntity> findAll() {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            List<ExpenseEntity> ExpenseList =
                    em.createQuery("select e from ExpenseEntity e", ExpenseEntity.class).getResultList();
            em.getTransaction().commit();
            return ExpenseList;
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(ExpenseEntity entity) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.remove(entity);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            IncomeEntity incomeEntity = em.find(IncomeEntity.class, id);
            em.remove(incomeEntity);
            em.getTransaction().commit();
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}
