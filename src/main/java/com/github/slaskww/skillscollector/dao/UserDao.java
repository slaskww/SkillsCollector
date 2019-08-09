package com.github.slaskww.skillscollector.dao;

import com.github.slaskww.skillscollector.dto.Skill;
import com.github.slaskww.skillscollector.dto.Source;
import com.github.slaskww.skillscollector.dto.User;
import org.hibernate.SessionFactory;

import java.util.Collection;
import java.util.List;


public class UserDao extends BaseDao {

    public UserDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public User get(Long id) {
        return super.produceInTransaction(session -> session.get(User.class, id));
    }

    public void save(User user) {
        super.executeInTransaction(session -> session.save(user));
    }

    public void update(User user) {
        super.executeInTransaction(session -> session.update(user));
    }

    public void delete(User user) {
        super.executeInTransaction(session -> session.delete(user));
    }

    public Boolean isUsernameAvailable(String username) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT count(u) FROM User u WHERE u.username = :username", Long.class)
                        .setParameter("username", username)
                        .getSingleResult() <= 0
        );
    }

    public List<User> getAll() {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM User u", User.class)
                        .getResultList());
    }

    public List<User> getAllByUsername(String username) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class)
                        .setParameter("username", username)
                        .getResultList());
    }

    public List<User> getAllByUsernameAndPassword(String username, String password) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class)
                        .setParameter("username", username)
                        .setParameter("password", password)
                        .getResultList());
    }

    public List<String> getAllSkillsForUser(String username, String password) {
        return super.produceInTransaction(
                session -> session.createQuery(
                        "SELECT sk.name " +
                                "FROM Source so join so.skills sk  " +
                                "WHERE so.id in (" +
                                "Select s.id From User u join u.sources s Where u.username = :username and u.password = :password)", String.class)
                        .setParameter("username", username)
                        .setParameter("password", password)
                        .getResultList());
    }

    public List<Source> getAllSourcesForUser(String username, String password){
        return super.produceInTransaction(
                session -> session.createQuery(
                        "Select s " +
                                "From User u join u.sources s " +
                                "Where u.username = :username and u.password = :password", Source.class)
        .setParameter("username", username)
        .setParameter("password", password)
        .getResultList());
    }

    public void addNewSource(User user, Source source){
        super.executeInTransaction(session -> {
            Source sourceProxy = session.get(Source.class, source.getId());

            user.getSources().add(sourceProxy);
            sourceProxy.getUsers().add(user);

            session.update(sourceProxy);
            session.update(user);
        });

    }
}
