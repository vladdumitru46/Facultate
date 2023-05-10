package com.example;

import com.example.interfaces.IRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RepoTask implements IRepository<Task, Integer> {
    private final JDBC jdbc = new JDBC();

    @Override
    public Task add(Task entity) {
        String query = "INSERT INTO task(name, description, deadline) VALUES(?,?,?)";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDate(3, Date.valueOf(entity.getDeadline()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return entity;
    }

    @Override
    public Task delete(Integer integer) {
        String query = "DELETE FROM task WHERE id = integer";
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
    public Task update(Task entity) {
        String query = "UPDATE task SET name=?, description=?, deadline=? WHERE id=?";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDate(3, Date.valueOf(entity.getDeadline()));
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public Task findOne(Integer integer) {
        String query = "SELECT * FROM task WHERE id=?";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                LocalDate date = resultSet.getDate("deadline").toLocalDate();
                Task task = new Task(name, description, date);
                task.setId(integer);
                return task;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public Iterable<Task> findAll() {
        List<Task> list = new ArrayList<>();
        String query = "SELECT * FROM task";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                LocalDate date = resultSet.getDate("deadline").toLocalDate();
                Task task = new Task(name, description, date);
                task.setId(id);
                list.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return list;
    }
}
