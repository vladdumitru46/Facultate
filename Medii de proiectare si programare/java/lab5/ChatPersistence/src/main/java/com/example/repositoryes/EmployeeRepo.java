package com.example.repositoryes;

import com.example.EmployeeAtOffice;
import com.example.repositoryes.interfaces.RepoEmployee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeeRepo implements RepoEmployee {

    private final JDBCUtils jdbcUtils = new JDBCUtils();
//    private static final Logger logger = LogManager.getLogger();

    public EmployeeRepo(Properties properties) {
//        logger.info("\"Initializing EmployeeRepo with properties: {} \"", properties);
    }

    @Override
    public boolean logIn(String email, String password) {
//        logger.traceEntry("log in");
//        logger.traceExit();
        return findOneByEmailAndPassword(email, password) != null;

    }

    @Override
    public void logOut() {

    }

    @Override
    public EmployeeAtOffice findOneByEmailAndPassword(String email, String password) {
//        logger.traceEntry("find one by email and password {} {}", email, password);
        String query = "SELECT * FROM employee WHERE email=? and password = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                return new EmployeeAtOffice(id, name, email, password);
            }
        } catch (SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
        return null;
    }

    @Override
    public EmployeeAtOffice findOne(Integer id) {
//        logger.traceEntry("find one {}", id);
        String query = "SELECT * FROM employee WHERE id=?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                return new EmployeeAtOffice(id, name, email, password);
            }
        } catch (SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
        return null;
    }

    @Override
    public Iterable<EmployeeAtOffice> findAll() {
//        logger.traceEntry("find all");
        List<EmployeeAtOffice> list = new ArrayList<>();
        String query = "SELECT * FROM employee";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                EmployeeAtOffice employeeAtOffice = new EmployeeAtOffice(id, name, email, password);
                list.add(employeeAtOffice);
            }
        } catch (SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
        return list;
    }

    @Override
    public EmployeeAtOffice add(EmployeeAtOffice entity) {
//        logger.traceEntry("add {}", entity);
        String query = "INSERT INTO employee(id, name, email, password) values(?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity.getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, entity.getPassword());
            statement.executeUpdate();
        } catch (
                SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
        return entity;
    }

    @Override
    public EmployeeAtOffice delete(Integer entity) {
//        logger.traceEntry("delete {}", entity);
        String query = "DELETE FROM employee WHERE id=?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, entity);
            statement.executeUpdate();
        } catch (
                SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
        return findOne(entity);
    }

    @Override
    public EmployeeAtOffice update(EmployeeAtOffice entity) {
//        logger.traceEntry("update {}", entity);
        String query = "UPDATE employee SET name=?, email=?, password=? WHERE id=?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getEmail());
            statement.setString(3, entity.getPassword());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (
                SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
        return entity;
    }
}
