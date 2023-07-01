package com.example.repository;

import com.example.EmployeeAndArrivalTime;
import com.example.interfaces.IRepoEmployeeLogInTime;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoEmployeeLogInTime implements IRepoEmployeeLogInTime {
    @Override
    public EmployeeAndArrivalTime add(EmployeeAndArrivalTime entity) {
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
    public EmployeeAndArrivalTime delete(Integer integer) {
        EmployeeAndArrivalTime employeeAndArrivalTime = findOne(integer);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                EmployeeAndArrivalTime criteria = session.createQuery("from EmployeeAndArrivalTime where id = :entity", EmployeeAndArrivalTime.class).
                        setParameter("entity", integer).setMaxResults(1).uniqueResult();
                session.delete(criteria);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return employeeAndArrivalTime;
    }

    @Override
    public EmployeeAndArrivalTime update(EmployeeAndArrivalTime entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                EmployeeAndArrivalTime employeeAndArrivalTime = session.load(EmployeeAndArrivalTime.class, entity.getId());
                employeeAndArrivalTime.setEmployeeId(entity.getEmployeeId());
                employeeAndArrivalTime.setTaskId(entity.getTaskId());
                employeeAndArrivalTime.setLogInTime(entity.getLogInTime());
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
    public EmployeeAndArrivalTime findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<EmployeeAndArrivalTime> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<EmployeeAndArrivalTime> list = session.createQuery("FROM EmployeeAndArrivalTime ", EmployeeAndArrivalTime.class).stream().toList();
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
    public EmployeeAndArrivalTime findByEmployeeId(Integer id) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                EmployeeAndArrivalTime employee = session.createQuery("FROM EmployeeAndArrivalTime WHERE employeeId=:id", EmployeeAndArrivalTime.class).
                        setParameter("id", id).setMaxResults(1).uniqueResult();
                transaction.commit();
                return employee;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
        return null;
    }
}
