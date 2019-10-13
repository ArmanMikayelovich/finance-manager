package com.energizeglobal.internship.dao;

import com.energizeglobal.internship.dao.interfaces.IncomeDao;
import com.energizeglobal.internship.entity.IncomeEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class incomeDaoJpaImpl implements IncomeDao {
    private final EntityManagerFactory factory;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public incomeDaoJpaImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public IncomeEntity save(IncomeEntity entity) {
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
    public IncomeEntity update(IncomeEntity income) {
        EntityManager em = factory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(income);
            em.persist(income);
            em.getTransaction().commit();
            return income;
        } catch (RuntimeException ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return null;
    }

    @Override
    public Optional<IncomeEntity> find(Long id) {
        EntityManager entityManager = factory.createEntityManager();

        try {
            entityManager.getTransaction().begin();
            IncomeEntity incomeEntity = entityManager.find(IncomeEntity.class, id);
            entityManager.getTransaction().commit();
            return Optional.of(incomeEntity);
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public List<IncomeEntity> findAll() {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<IncomeEntity> incomeList =
                    entityManager.createQuery("select i from  IncomeEntity i", IncomeEntity.class).getResultList();
            entityManager.getTransaction().commit();
            return incomeList;
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();

    }

    @Override
    public void delete(IncomeEntity entity) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Integer id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            IncomeEntity incomeEntity = entityManager.find(IncomeEntity.class, id);
            entityManager.remove(incomeEntity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }
}
