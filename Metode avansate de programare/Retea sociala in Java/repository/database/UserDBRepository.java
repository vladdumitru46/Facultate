package com.example.reteasocialagui.example.repository.database;

import com.example.reteasocialagui.example.crypt.CryptPassword;
import com.example.reteasocialagui.example.domain.Entity;
import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.domain.validators.Validator;
import com.example.reteasocialagui.example.repository.Repository;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserDBRepository<ID, E extends Entity<ID>> implements Repository<Integer, User> {

    private final JDBCUtils jdbcUtils = new JDBCUtils();

    Validator<User> validator;

    public UserDBRepository(Validator<User> validator) {
        this.validator = validator;
    }

    public User findOne(Integer id) {
        String query = "SELECT * from users";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                if (Objects.equals(id, id1)) {
                    User user = new User(first_name, last_name);
                    user.setId(id);
                    user.setEmail(email);
                    user.setPassword(password);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findOneByName(String firstName, String secondName) {
        String query = "SELECT * from users";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                if (first_name.equals(firstName) && last_name.equals(secondName)) {
                    User user = new User(first_name, last_name);
                    user.setId(id1);
                    user.setEmail(email);
                    user.setPassword(password);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findONeByEmailAndPassword(String email, String password) {
        String query = "SELECT * from users";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String em = resultSet.getString("email");
                String pssd = resultSet.getString("password");
                if (em.equals(email) && CryptPassword.toHexString(CryptPassword.getSHA(password)).equals(pssd)) {
                    User user = new User(first_name, last_name);
                    user.setId(id1);
                    user.setEmail(em);
                    user.setPassword(password);
                    return user;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Iterable<User> findAll() {
        Set<User> set = new HashSet<>();

        String query = "SELECT * from users";
        try (Connection connection = jdbcUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                User user = new User(first_name, last_name);
                user.setId(id);
                user.setEmail(email);
                user.setPassword(password);
                set.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }

    @Override
    public Iterable<User> findAllUsersWithOddId() {
        return null;
    }

    @Override
    public Iterable<User> findAllUsersWithEvenId() {
        return null;
    }

    public Map<Integer, User> get_all() {
        Map<Integer, User> map = new HashMap<>();
        String query = "SELECT * from users";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                User user = new User(first_name, last_name);
                user.setId(id);
                user.setEmail(email);
                user.setPassword(password);
                map.put(id, user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public User save(Integer id, User entity) {
        String query = "INSERT INTO users(id, first_name, last_name, email, password) VALUES (?,?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, entity.getFirstName());
            statement.setString(3, entity.getLastName());
            statement.setString(4, entity.getEmail());
            statement.setString(5, CryptPassword.toHexString(CryptPassword.getSHA(entity.getPassword())));
            statement.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }


    public User delete(Integer id) {
        String query = "DELETE FROM users WHERE id = ?";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return findOne(id);
    }

    @Override
    public User update(User entity) {
        String query = "UPDATE users SET first_name=?,last_name=?, email=?, password=? WHERE id=?";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getLastName());
            statement.setString(3, entity.getEmail());
            statement.setString(4, CryptPassword.toHexString(CryptPassword.getSHA(entity.getPassword())));
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }
}
