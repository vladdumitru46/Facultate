package com.example.repository;

import com.example.Employee;
import com.example.interfaces.IRepoEmployee;
import com.example.utils.Factory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RepoEmployee implements IRepoEmployee {
    @Override
    public Employee findByEmailAndPassword(String email, String password) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Employee employee = session.createQuery("from Employee where email = :email and password= :password", Employee.class).
                        setParameter("email", email).setParameter("password", password).setMaxResults(1).uniqueResult();
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

    @Override
    public Employee add(Employee entity) {
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
    public Employee delete(Integer integer) {
        Employee employee = findOne(integer);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Employee criteria = session.createQuery("from Employee where id = :entity", Employee.class).
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
        return employee;
    }

    @Override
    public Employee update(Employee entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Employee employee = session.load(Employee.class, entity.getId());
                employee.setName(entity.getName());
                employee.setEmail(entity.getEmail());
                employee.setPassword(entity.getPassword());
                employee.setSalary(entity.getSalary());
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
    public Employee findOne(Integer integer) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Employee employee = session.createQuery("FROM Employee WHERE id=:id", Employee.class).
                        setParameter("id", integer).setMaxResults(1).uniqueResult();
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

    @Override
    public Iterable<Employee> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<Employee> list = session.createQuery("FROM Employee", Employee.class).stream().toList();
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
