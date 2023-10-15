package com.example.reteasocialagui.example.repository.database;

import com.example.reteasocialagui.example.domain.Messages;
import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.domain.validators.UserValidator;
import com.example.reteasocialagui.example.repository.RepoMessages;
import com.example.reteasocialagui.example.repository.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MessageDBRepo implements RepoMessages {
    private final JDBCUtils jdbcUtils = new JDBCUtils();


    @Override
    public List<Messages> getMessagesBetweenUsers(User sender, User receiver) {
        List<Messages> messagesList = new ArrayList<>();
        String query = "SELECT * FROM messages";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id1");
                Integer id2 = resultSet.getInt("id2");
                String message = resultSet.getString("message");
                Repository<Integer, User> repo = new UserDBRepository<>(new UserValidator());
                if (Objects.equals(sender, repo.findOne(id1)) && Objects.equals(receiver, repo.findOne(id2)) || Objects.equals(receiver, repo.findOne(id1)) && Objects.equals(sender, repo.findOne(id2))) {
                    messagesList.add(new Messages(repo.findOne(id1), repo.findOne(id2), message));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messagesList;
    }

    @Override
    public void sendMessage(User sender, User receiver, String message) {
        String query = "INSERT INTO messages(id1, id2, message) VALUES (?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, sender.getId());
            statement.setInt(2, receiver.getId());
            statement.setString(3, message);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMessage(User sender, User receiver, String message) {
        String query = "DELETE FROM messages where id1=? and id2=? and message=?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, sender.getId());
            statement.setInt(2, receiver.getId());
            statement.setString(3, message);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
