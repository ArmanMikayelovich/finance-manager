package com.energizeglobal.internship.dao;

import com.energizeglobal.internship.dao.interfaces.UserDao;
import com.energizeglobal.internship.entity.ExpenseEntity;
import com.energizeglobal.internship.entity.IncomeEntity;
import com.energizeglobal.internship.entity.UserEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

@Repository
public class UserDaoJpaImpl implements UserDao {
    private final EntityManagerFactory factory;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    public UserDaoJpaImpl(EntityManagerFactory factory) {
        this.factory = factory;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(userEntity);
            entityManager.getTransaction().commit();
            return userEntity;
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(userEntity);
            entityManager.persist(userEntity);
            entityManager.getTransaction().commit();
            return userEntity;
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return null;
    }

    @Override
    public Optional<UserEntity> find(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        UserEntity userEntity = null;
        try {
            entityManager.getTransaction().begin();
            userEntity = entityManager.find(UserEntity.class, id);
            entityManager.getTransaction().commit();
            return Optional.of(userEntity);
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Optional.empty();
    }

    @Override
    public List<UserEntity> findAll() {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            List<UserEntity> userList = entityManager.createQuery("select u from UserEntity u", UserEntity.class).getResultList();
            entityManager.getTransaction().commit();
            return userList;
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void delete(UserEntity userEntity) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(userEntity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void delete(Long id) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            UserEntity userEntity = entityManager.find(UserEntity.class, id);
            entityManager.remove(userEntity);
            entityManager.getTransaction().commit();
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<ExpenseEntity> getAllCosts(UserEntity user) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            user = entityManager.merge(user);
            List<ExpenseEntity> costs = user.getCosts();
            List<ExpenseEntity> returnList = new ArrayList<>(costs);
            entityManager.getTransaction().commit();
            return returnList;
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public List<IncomeEntity> getAllIncomes(UserEntity user) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            user = entityManager.merge(user);
            List<IncomeEntity> incomes = user.getIncomes();
            List<IncomeEntity> returnList = new ArrayList<>(incomes);
            entityManager.getTransaction().commit();
            return returnList;
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
        return Collections.emptyList();
    }

    @Override
    public void addExpense(UserEntity user, ExpenseEntity expense) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            user = entityManager.merge(user);
            user.getCosts().add(expense);
            entityManager.getTransaction().commit();
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void addIncome(UserEntity user, IncomeEntity income) {
        EntityManager entityManager = factory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            user = entityManager.merge(user);
            user.getIncomes().add(income);
            entityManager.getTransaction().commit();
        } catch (RuntimeException ex) {
            entityManager.getTransaction().rollback();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<IncomeEntity> getPastIncomes(UserEntity user) {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<IncomeEntity> query = manager.createQuery("Select income from IncomeEntity income " +
                        "where income.user.id = :id and income.date <= :date",
                IncomeEntity.class);
        query.setParameter("id", user.getId());
        query.setParameter("date", new Date());
        List<IncomeEntity> resultList = query.getResultList();
        manager.close();
        return resultList;
    }

    @Override
    public List<ExpenseEntity> getPastCosts(UserEntity user) {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<ExpenseEntity> query = manager.createQuery("Select expense from ExpenseEntity expense " +
                        "where expense.user.id = :id and expense.date <= :date",
                ExpenseEntity.class);
        query.setParameter("id", user.getId());
        query.setParameter("date", new Date());
        List<ExpenseEntity> resultList = query.getResultList();
        manager.close();
        return resultList;
    }

    @Override
    public List<IncomeEntity> getFutureIncomes(UserEntity user) {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<IncomeEntity> query = manager.createQuery("Select income from IncomeEntity income " +
                        "where income.user.id = :id and income.date > :date",
                IncomeEntity.class);
        query.setParameter("id", user.getId());
        query.setParameter("date", new Date());
        List<IncomeEntity> resultList = query.getResultList();
        manager.close();
        return resultList;
    }

    @Override
    public List<ExpenseEntity> getFutureCosts(UserEntity user) {
        EntityManager manager = factory.createEntityManager();
        TypedQuery<ExpenseEntity> query = manager.createQuery("Select expense from ExpenseEntity expense " +
                        "where expense.user.id = :id and expense.date > :date",
                ExpenseEntity.class);
        query.setParameter("id", user.getId());
        query.setParameter("date", new Date());
        List<ExpenseEntity> resultList = query.getResultList();
        manager.close();
        return resultList;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        TypedQuery<UserEntity> query = manager.createQuery("SELECT u from UserEntity u where u.email = :email", UserEntity.class);
        query.setParameter("email", email);
        UserEntity result = query.getSingleResult();
        return Optional.of(result);
    }
}
