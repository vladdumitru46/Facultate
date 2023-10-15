package com.example.repositoryes;

import com.example.Artist;
import com.example.repositoryes.interfaces.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ArtistRepo implements Repository<String, Artist> {

    private final JDBCUtils jdbcUtils = new JDBCUtils();
//    private static final Logger logger = LogManager.getLogger();

    public ArtistRepo(Properties properties) {
//        logger.info("\"Initializing ArtistRepo with properties: {} \"", properties);
    }


    @Override
    public Artist findOne(String s) {
        //logger.traceEntry("find one {}", s);
        String query = "SELECT * FROM artists WHERE \"stageName\" = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, s);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                return new Artist(name, s, age);
            }
        } catch (SQLException e) {
            //logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
        //logger.traceExit();
        return null;
    }

    @Override
    public Iterable<Artist> findAll() {
//        logger.traceEntry("find all");
        List<Artist> list = new ArrayList<>();
        String query = "SELECT * FROM artists";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String stageName = resultSet.getString("stageName");
                Integer age = resultSet.getInt("age");
                list.add(new Artist(name, stageName, age));
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
    public Artist add(Artist entity) {
//        logger.traceEntry("add {}", entity);
        String query = "INSERT INTO artists(name, \"stageName\", age) values (?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getStageName());
            statement.setInt(3, entity.getAge());
            statement.executeUpdate();
        } catch (SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
        return entity;
    }

    @Override
    public Artist delete(String entity) {
//        logger.traceEntry("delete {}", entity);
        String query = "DELETE FROM artists WHERE \"stageName\" = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity);
            statement.executeUpdate();
        } catch (SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
        return findOne(entity);
    }

    @Override
    public Artist update(Artist entity) {
//        logger.traceEntry("update {}", entity);
        String query = "Update artits SET name=?, age=? where \"stageName\" = ?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, entity.getStageName());
            statement.setInt(2, entity.getAge());
            statement.setString(3, entity.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
//            logger.error(e);
            e.printStackTrace();
            System.out.println("Error: " + e);
        }
//        logger.traceExit();
        return entity;
    }
}
