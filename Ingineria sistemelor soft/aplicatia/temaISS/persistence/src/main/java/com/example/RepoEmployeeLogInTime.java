package com.example;

import com.example.interfaces.IRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepoEmployeeLogInTime implements IRepository<EmployeeAndArrivalTime, Integer> {
    private final JDBC jdbc = new JDBC();


    @Override
    public EmployeeAndArrivalTime add(EmployeeAndArrivalTime entity) {
        String query = "INSERT INTO employee_log_in_time(employee_id, task_id, log_in_time) VALUES(?,?,?)";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getEmployeeId());
            statement.setInt(2, entity.getTaskId());
            statement.setTime(3, Time.valueOf(entity.getLogInTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return entity;
    }

    @Override
    public EmployeeAndArrivalTime delete(Integer integer) {
        String query = "DELETE FROM employee_log_in_time WHERE id=?";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, integer);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public EmployeeAndArrivalTime update(EmployeeAndArrivalTime entity) {
        String query = "UPDATE employee_log_in_time SET employee_id=?, task_id=?, log_in_time=? WHERE id=?";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getEmployeeId());
            statement.setInt(2, entity.getTaskId());
            statement.setTime(3, Time.valueOf(entity.getLogInTime()));
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public EmployeeAndArrivalTime findOne(Integer integer) {
        return null;
    }

    @Override
    public Iterable<EmployeeAndArrivalTime> findAll() {
        List<EmployeeAndArrivalTime> list = new ArrayList<>();
        String query = "SELECT * FROM employee_log_in_time";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Integer idEmployee = resultSet.getInt("id_employee");
                Integer idTask = resultSet.getInt("id_task");
                Time logInTime = resultSet.getTime("log_in_time");
                EmployeeAndArrivalTime task = new EmployeeAndArrivalTime(idEmployee, idTask, logInTime.toLocalTime());
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
