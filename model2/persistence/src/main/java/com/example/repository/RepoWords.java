package com.example.repository;

import com.example.Words;
import com.example.interfaces.IRepoWords;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoWords implements IRepoWords {
    @Override
    public Words add(Words entity) {
        return null;
    }

    @Override
    public Words delete(Integer integer) {
        return null;
    }

    @Override
    public Words update(Words entity) {
        return null;
    }

    @Override
    public Words findOne(Integer integer) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Words words = session.createQuery("FROM Words WHERE id=:id", Words.class).
                        setParameter("id", integer).setMaxResults(1).uniqueResult();
                transaction.commit();
                return words;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<Words> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Words> list = session.createQuery("FROM Words ", Words.class).stream().toList();
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
}
