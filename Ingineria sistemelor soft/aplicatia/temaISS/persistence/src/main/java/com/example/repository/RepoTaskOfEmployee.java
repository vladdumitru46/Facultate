package com.example.repository;

import com.example.TaskOfEmployee;
import com.example.interfaces.IRepoTaskOfEmployee;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoTaskOfEmployee implements IRepoTaskOfEmployee {


    @Override
    public TaskOfEmployee add(TaskOfEmployee entity) {
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
    public TaskOfEmployee delete(Integer integer) {
        TaskOfEmployee taskOfEmployee = findOne(integer);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                TaskOfEmployee criteria = session.createQuery("from TaskOfEmployee where id = :entity", TaskOfEmployee.class).setParameter("entity", integer).setMaxResults(1).uniqueResult();
                session.delete(criteria);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return taskOfEmployee;
    }

    @Override
    public TaskOfEmployee update(TaskOfEmployee entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                TaskOfEmployee taskOfEmployee = session.load(TaskOfEmployee.class, entity.getId());
                taskOfEmployee.setEmployeeId(entity.getEmployeeId());
                taskOfEmployee.setTaskId(entity.getTaskId());
                taskOfEmployee.setTaskStatus(entity.getTaskStatus());
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
    public TaskOfEmployee findOne(Integer integer) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                TaskOfEmployee taskOfEmployee = session.createQuery("FROM TaskOfEmployee WHERE id=:id", TaskOfEmployee.class).
                        setParameter("id", integer).setMaxResults(1).uniqueResult();
                transaction.commit();
                return taskOfEmployee;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }

    @Override
    public Iterable<TaskOfEmployee> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<TaskOfEmployee> list = session.createQuery("FROM TaskOfEmployee", TaskOfEmployee.class).stream().toList();
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
    public List<TaskOfEmployee> findAllTasksForEmployee(Integer id) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<TaskOfEmployee> list = session.createQuery("FROM TaskOfEmployee where employeeId=:id", TaskOfEmployee.class).setParameter("id", id).stream().toList();
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
