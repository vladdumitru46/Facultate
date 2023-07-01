package com.example.repository;

import com.example.Clue;
import com.example.interfaces.IRepoClue;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RepoClue implements IRepoClue {

    @Override
    public Clue add(Clue entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(entity);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return entity;
    }

    @Override
    public Clue delete(Integer integer) {
        Clue clue = findOne(integer);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Clue criteria = session.createQuery("from Clue where id = :entity", Clue.class)
                        .setParameter("entity", integer).setMaxResults(1).uniqueResult();
                session.delete(criteria);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return clue;
    }

    @Override
    public Clue update(Clue entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Clue clue = session.load(Clue.class, entity.getId());
                clue.setPositionColumn(entity.getPositionColumn());
                clue.setPositionLine(entity.getPositionLine());
                clue.setTextOfClue(entity.getTextOfClue());
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return entity;
    }

    @Override
    public Clue findOne(Integer integer) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Clue clue = session.createQuery("FROM Clue WHERE id=:id", Clue.class).
                        setParameter("id", integer).setMaxResults(1).uniqueResult();
                transaction.commit();
                return clue;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<Clue> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Clue> list = session.createQuery("FROM Clue", Clue.class).stream().toList();
                transaction.commit();
                return list;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public Clue findOneByPosition(Integer positionLine, Integer positionColumn) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Clue clue = session.createQuery("FROM Clue WHERE positionLine=:positionLine and positionColumn = :positionColumn", Clue.class).
                        setParameter("positionLine", positionLine)
                        .setParameter("positionColumn", positionColumn)
                        .setMaxResults(1).uniqueResult();
                transaction.commit();
                return clue;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }
}
