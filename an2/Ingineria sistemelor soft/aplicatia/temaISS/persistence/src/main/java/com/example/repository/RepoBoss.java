package com.example.repository;

import com.example.Boss;
import com.example.interfaces.IRepoBoss;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoBoss implements IRepoBoss {

    @Override
    public Boss findByEmailAndPassword(String email, String password) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Boss boss = session.createQuery("from Boss where email = :email and password= :password", Boss.class).
                        setParameter("email", email).setParameter("password", password).setMaxResults(1).uniqueResult();
                transaction.commit();
                return boss;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Boss add(Boss entity) {
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
    public Boss delete(Integer integer) {
        Boss boss = findOne(integer);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Boss criteria = session.createQuery("from Boss where id = :entity", Boss.class).setParameter("entity", integer).setMaxResults(1).uniqueResult();
                session.delete(criteria);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return boss;
    }

    @Override
    public Boss update(Boss entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Boss boss = session.load(Boss.class, entity.getId());
                boss.setName(entity.getName());
                boss.setEmail(entity.getEmail());
                boss.setPassword(entity.getPassword());
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
    public Boss findOne(Integer integer) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Boss boss = session.createQuery("FROM Boss WHERE id=:id", Boss.class).
                        setParameter("id", integer).setMaxResults(1).uniqueResult();
                transaction.commit();
                return boss;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<Boss> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Boss> list = session.createQuery("FROM Boss", Boss.class).stream().toList();
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
