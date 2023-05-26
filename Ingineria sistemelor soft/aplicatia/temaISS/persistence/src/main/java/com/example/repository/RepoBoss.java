package com.example.repository;

import com.example.Boss;
import com.example.interfaces.IRepoBoss;
import com.example.utils.Factory;
import com.example.utils.JDBC;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoBoss implements IRepoBoss {

    JDBC jdbc = new JDBC();

    @Override
    public Boss findByEmailAndPassword(String email, String password) {
        try (Session session = Factory.getProperties()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Boss boss = session.createQuery("FROM Boss where email=:email AND password=:password", Boss.class).
                        setParameter("email", email).setParameter("password", password).setMaxResults(1).uniqueResult();
                transaction.commit();
                return boss;
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
    public Boss add(Boss entity) {
        String query = "INSERT INTO boss(name, email, password) VALUES(?,?,?)";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return entity;
    }

    @Override
    public Boss delete(Integer integer) {
        String query = "DELETE FROM boss WHERE id = integer";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, integer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return findOne(integer);
    }

    @Override
    public Boss update(Boss entity) {
        String query = "UPDATE boss SET name=?, email=?, password=? WHERE id=?";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public Boss findOne(Integer integer) {
        String query = "SELECT * FROM boss WHERE id=?";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Boss boss = new Boss(name, email, password);
                boss.setId(integer);
                return boss;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public Iterable<Boss> findAll() {
        List<Boss> list = new ArrayList<>();
        String query = "SELECT * FROM boss";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Boss boss = new Boss(name, email, password);
                boss.setId(id);
                list.add(boss);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return list;
    }
}
