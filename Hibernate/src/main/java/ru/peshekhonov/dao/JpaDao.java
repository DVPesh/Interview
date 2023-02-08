package ru.peshekhonov.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.peshekhonov.config.JpaDaoConfig;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public abstract class JpaDao<K, T> {

    private final JpaDaoConfig JPA_DAO_CONFIG;
    private Class<?> entityClass;

    public JpaDao() {
        JPA_DAO_CONFIG = JpaDaoConfig.getInstance();
        setEntityClass();
    }

    private void setEntityClass() {
        entityClass = (Class<?>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public Optional<T> get(K id) {
        return executeForSession(session ->
                Optional.ofNullable((T) session.find(entityClass, id)));
    }

    public List<T> getAll() {
        return (List<T>) executeForSession(session -> session.createQuery("from " + entityClass.getName(), entityClass).stream().toList());
    }

    public void save(T t) {
        executeInTransaction(session -> {
            session.persist(t);
        });
    }

    public T update(T t) {
        return executeInTransaction(session -> {
            return session.merge(t);
        });
    }

    public void delete(T t) {
        executeInTransaction(session -> {
            session.remove(t);
        });
    }

    private <R> R executeForSession(Function<Session, R> function) {
        return function.apply(JPA_DAO_CONFIG.getSession());
    }

    private void executeInTransaction(Consumer<Session> consumer) {
        Session session = JPA_DAO_CONFIG.getSession();
        Transaction transaction = session.getTransaction();
        if (transaction.isActive()) {
            consumer.accept(session);
            return;
        }
        try {
            transaction.begin();
            consumer.accept(session);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
    }

    private <R> R executeInTransaction(Function<Session, R> function) {
        Session session = JPA_DAO_CONFIG.getSession();
        Transaction transaction = session.getTransaction();
        if (transaction.isActive()) {
            return function.apply(session);
        }
        try {
            transaction.begin();
            R result = function.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return null;
    }

    public List<Type> getGenericTypes() {
        return Arrays.stream(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()).toList();
    }
}
