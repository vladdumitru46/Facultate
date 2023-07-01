package com.example.repositoryes;

import com.example.EmployeeAtOffice;
import com.example.repositoryes.interfaces.RepoEmployee;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Properties;

public class EmployeeRepo implements RepoEmployee {

    private final JDBCUtils jdbcUtils = new JDBCUtils();
    private final Factory factory = new Factory();

    public EmployeeRepo(Properties properties) {
    }

    @Override
    public boolean logIn(String email, String password) {
        return findOneByEmailAndPassword(email, password) != null;

    }

    @Override
    public void logOut() {

    }

    @Override
    public EmployeeAtOffice findOneByEmailAndPassword(String email, String password) {
        try (Session session = factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                EmployeeAtOffice employee = session.createQuery("from EmployeeAtOffice where email = :email and password=:password", EmployeeAtOffice.class).
                        setParameter("email", email).setParameter("password", password).setMaxResults(1).uniqueResult();
                transaction.commit();
                return employee;
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
    public EmployeeAtOffice findOne(Integer id) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                EmployeeAtOffice employee = session.get(EmployeeAtOffice.class, id);
                transaction.commit();
                return employee;
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
    public Iterable<EmployeeAtOffice> findAll() {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                List<EmployeeAtOffice> list = session.createQuery("from EmployeeAtOffice", EmployeeAtOffice.class).stream().toList();
//                list.forEach(employee -> Hibernate.initialize(p.ge));
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
    public EmployeeAtOffice add(EmployeeAtOffice entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                session.save(entity);
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
    public EmployeeAtOffice delete(Integer entity) {
        EmployeeAtOffice employeeAtOffice = findOne(entity);
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                EmployeeAtOffice criteria = (EmployeeAtOffice) session.createQuery("from EmployeeAtOffice where id = :entity", EmployeeAtOffice.class).setParameter("entity", entity).setMaxResults(1).uniqueResult();
                session.delete(criteria);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return employeeAtOffice;
    }

    @Override
    public EmployeeAtOffice update(EmployeeAtOffice entity) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                EmployeeAtOffice employeeAtOffice = session.load(EmployeeAtOffice.class, entity.getId());
                employeeAtOffice.setName(entity.getName());
                employeeAtOffice.setEmail(entity.getEmail());
                employeeAtOffice.setPassword(entity.getPassword());
                transaction.commit();
                return entity;
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                    return null;
                }
            }
        }
        return entity;
    }
}
