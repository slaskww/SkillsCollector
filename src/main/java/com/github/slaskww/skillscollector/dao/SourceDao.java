package com.github.slaskww.skillscollector.dao;

import com.github.slaskww.skillscollector.model.Skill;
import com.github.slaskww.skillscollector.model.Source;
import org.hibernate.SessionFactory;

import java.util.List;

public class SourceDao extends BaseDao{

    public SourceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Source get(Long id) {
        return super.produceInTransaction(session -> session.get(Source.class, id));
    }

    public void save(Source source) {
        super.executeInTransaction(session -> session.save(source));
    }

    public void update(Source source) {
        super.executeInTransaction(session -> session.update(source));
    }

    public void delete(Source source) {
        super.executeInTransaction(session -> session.delete(source));
    }

    public Boolean isSourcevailable(Source sourceName) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT count(s) FROM Source s WHERE s.name = :sourceName", Long.class)
                        .setParameter("sourceName", sourceName)
                        .getSingleResult() <= 0
        );
    }

    public List<Source> getAll() {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT s FROM Source s", Source.class)
                        .getResultList());
    }

    public List<Source> getAllBySourceName(Source sourceName) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT s FROM Source s WHERE s.name = :sourceName", Source.class)
                        .setParameter("sourceName", sourceName)
                        .getResultList());
    }


}
