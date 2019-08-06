package com.github.slaskww.skillscollector.dao;

import com.github.slaskww.skillscollector.dto.Skill;
import org.hibernate.SessionFactory;

import java.util.List;

public class SkillDao extends BaseDao{

    public SkillDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Skill get(Long id) {
        return super.produceInTransaction(session -> session.get(Skill.class, id));
    }

    public void save(Skill skill) {
        super.executeInTransaction(session -> session.save(skill));
    }

    public void update(Skill skill) {
        super.executeInTransaction(session -> session.update(skill));
    }

    public void delete(Skill skill) {
        super.executeInTransaction(session -> session.delete(skill));
    }

    public Boolean isSkillAvailable(Skill skillName) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT count(s) FROM Skill s WHERE s.name = :skillName", Long.class)
                        .setParameter("skillName", skillName)
                        .getSingleResult() <= 0
        );
    }

    public List<Skill> getAll() {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT s FROM Skill s", Skill.class)
                        .getResultList());
    }

    public List<Skill> getAllBySkillName(Skill skillName) {
        return super.produceInTransaction(
                session -> session.createQuery("SELECT s FROM Skill s WHERE s.name = :skillName", Skill.class)
                        .setParameter("skillName", skillName)
                        .getResultList());
    }

}
