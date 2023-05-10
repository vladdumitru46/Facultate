package com.example;

import com.example.interfaces.IRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoTaskOfEmployee implements IRepository<TaskOfEmployee, Integer> {

    private final JDBC jdbc = new JDBC();

    @Override
    public TaskOfEmployee add(TaskOfEmployee entity) {
        String query = "INSERT INTO task_of_employees(id_employee, id_task, status) VALUES(?,?,?)";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getEmployeeId());
            statement.setInt(2, entity.getTaskId());
            statement.setString(3, String.valueOf(entity.getTaskStatus()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return entity;
    }

    @Override
    public TaskOfEmployee delete(Integer integer) {
        return null;
    }

    @Override
    public TaskOfEmployee update(TaskOfEmployee entity) {
        String query = "UPDATE task_of_employees SET id_employee=?, id_task=?, status=? WHERE id=?";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getEmployeeId());
            statement.setInt(2, entity.getTaskId());
            statement.setString(3, String.valueOf(entity.getTaskStatus()));
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public TaskOfEmployee findOne(Integer integer) {
        String query = "SELECT * FROM task_of_employees WHERE id=?";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer idEmployee = resultSet.getInt("id_employee");
                Integer idTask = resultSet.getInt("id_task");
                TaskStatus taskStatus = TaskStatus.valueOf(resultSet.getString("status"));
                TaskOfEmployee task = new TaskOfEmployee(idEmployee, idTask, taskStatus);
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
    public Iterable<TaskOfEmployee> findAll() {
        List<TaskOfEmployee> list = new ArrayList<>();
        String query = "SELECT * FROM task_of_employees";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer idEmployee = resultSet.getInt("id_employee");
                Integer idTask = resultSet.getInt("id_task");
                TaskStatus taskStatus = TaskStatus.valueOf(resultSet.getString("status"));
                TaskOfEmployee task = new TaskOfEmployee(idEmployee, idTask, taskStatus);
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
