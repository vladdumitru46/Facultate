package com.example.repository;

import com.example.Task;
import com.example.interfaces.IRepository;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoTask implements IRepository<Task, Integer> {
    @Override
    public Task add(Task entity) {
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
    public Task delete(Integer integer) {
        Task task = findOne(integer);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Task criteria = session.createQuery("from Task where id = :entity", Task.class).setParameter("entity", integer).setMaxResults(1).uniqueResult();
                session.delete(criteria);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return task;
    }

    @Override
    public Task update(Task entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Task task = session.load(Task.class, entity.getId());
                task.setName(entity.getName());
                task.setDescription(entity.getDescription());
                task.setDeadline(entity.getDeadline());
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
    public Task findOne(Integer integer) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Task task = session.createQuery("FROM Task WHERE id=:id", Task.class).
                        setParameter("id", integer).setMaxResults(1).uniqueResult();
                transaction.commit();
                return task;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<Task> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Task> list = session.createQuery("FROM Task", Task.class).stream().toList();
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
