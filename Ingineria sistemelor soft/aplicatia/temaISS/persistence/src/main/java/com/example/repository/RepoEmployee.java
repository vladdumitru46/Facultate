package com.example.repository;

import com.example.Employee;
import com.example.interfaces.IRepoEmployee;
import com.example.utils.JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepoEmployee implements IRepoEmployee {
    private final JDBC jdbc = new JDBC();

    @Override
    public Employee findByEmailAndPassword(String email, String password) {
        String query = "SELECT * FROM employee WHERE email=? AND password=?";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double salary = resultSet.getDouble("salary");
                Employee employee = new Employee(name, email, password, salary);
                employee.setId(id);
                return employee;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Employee add(Employee entity) {
        String query = "INSERT INTO employee(name, email, password, salary) VALUES(?,?,?,?)";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setDouble(4, entity.getSalary());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return entity;
    }

    @Override
    public Employee delete(Integer integer) {
        String query = "DELETE FROM employees WHERE id = integer";
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
    public Employee update(Employee entity) {
        String query = "UPDATE boss SET name=?, email=?, password=?, salary=? WHERE id=?";
        try (Connection connection = jdbc.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setDouble(4, entity.getSalary());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return entity;
    }

    @Override
    public Employee findOne(Integer integer) {
        String query = "SELECT * FROM employee WHERE id=?";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, integer);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Double salary = resultSet.getDouble("salary");
                Employee employee = new Employee(name, email, password, salary);
                employee.setId(integer);
                return employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return null;
    }

    @Override
    public Iterable<Employee> findAll() {
        List<Employee> list = new ArrayList<>();
        String query = "SELECT * FROM employee";
        try (Connection connection = jdbc.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Double salary = resultSet.getDouble("salary");
                Employee employee = new Employee(name, email, password, salary);
                employee.setId(id);
                list.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        return list;
    }
}
