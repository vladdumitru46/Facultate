package com.example.repository;

import com.example.User;
import com.example.interfaces.IRepoUser;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RepoUser implements IRepoUser {
    @Override
    public User findOneByAlias(String alias) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.createQuery("from User where userName=:alias", User.class).
                        setParameter("alias", alias).setMaxResults(1).uniqueResult();
                transaction.commit();
                return user;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public User add(User entity) {
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
    public User delete(Integer integer) {
        User user = findOne(integer);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User criteria = session.createQuery("from User where id = :entity", User.class)
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
        return user;
    }

    @Override
    public User update(User entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.load(User.class, entity.getId());
                user.setName(entity.getName());
                user.setUserName(entity.getUserName());
                user.setGameTime(entity.getGameTime());
                user.setNo_of_points(entity.getNo_of_points());
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
    public User findOne(Integer integer) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.createQuery("FROM User WHERE id=:id", User.class).
                        setParameter("id", integer).setMaxResults(1).uniqueResult();
                transaction.commit();
                return user;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<User> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<User> list = session.createQuery("FROM User", User.class).stream().toList();
                transaction.commit();
                List<User> users = new ArrayList<>(list);
                users.sort(Comparator.comparingInt(User::getNo_of_points).reversed());
                return users;
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
