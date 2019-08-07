package com.github.slaskww.skillscollector.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseDao {

    private static final Logger log = Logger.getLogger("BaseDao");

    private final SessionFactory sessionFactory;

    protected BaseDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //Function<Session, R>  - This is a functional interface and can therefore be used as the assignment target for a lambda expression or method reference.
    //Represents a function that accepts one argument (Session) and produces a result (R).
    //This is a functional interface whose functional method is apply(Object).
    protected <R> R produceInTransaction(Function<Session, R> function) {
        Transaction transaction = null;
        R result = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            result = function.apply(session);
            transaction.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Błąd wykonania executeInTransaction", ex);
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }
    //This is a functional interface and can therefore be used as the assignment target for a lambda expression or method reference.
    //Consumer<Session> - Represents an operation that accepts a single input argument and returns no result.
    //This is a functional interface whose functional method is accept(Object).
    protected void executeInTransaction(Consumer<Session> consumer) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            consumer.accept(session);
            transaction.commit();
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Błąd wykonania executeInTransaction", ex);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}