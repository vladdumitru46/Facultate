package com.example;

import com.example.interfaces.IRepoBoss;

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
        String query = "SELECT * FROM boss WHERE email=? AND password=?";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Boss boss = new Boss(name, email, password);
                boss.setId(id);
                return boss;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Boss add(Boss entity) {
        String query = "INSERT INTO boss(name, email, password) VALUES(?,?,?)";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
        return null;
    }

    @Override
    public Iterable<Boss> findAll() {
        return null;
    }
}
