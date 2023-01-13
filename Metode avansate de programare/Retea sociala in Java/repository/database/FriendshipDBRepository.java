package com.example.reteasocialagui.example.repository.database;

import com.example.reteasocialagui.example.domain.FriendRequests;
import com.example.reteasocialagui.example.domain.Friendship;
import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.domain.validators.UserValidator;
import com.example.reteasocialagui.example.repository.Repository;
import com.example.reteasocialagui.example.repository.memory.FriendshipRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class FriendshipDBRepository extends FriendshipRepository {
    private final JDBCUtils jdbcUtils = new JDBCUtils();

    public Map<Integer, Set<User>> get_all() {
        Map<Integer, Set<User>> map = new HashMap<>();
        String query = "SELECT * FROM friendship";
        try (Connection connection = jdbcUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(query); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id_user1");
                Integer id2 = resultSet.getInt("id_user2");
                Repository<Integer, User> repo = new UserDBRepository<>(new UserValidator());
                if (!map.containsKey(id1)) {
                    Set<User> set = new HashSet<>();
                    map.put(id1, set);
                }
                map.get(id1).add(repo.findOne(id2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public List<Friendship> getAll() {
        List<Friendship> list = new ArrayList<>();
        String query = "SELECT * FROM friendship";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id_user1");
                Integer id2 = resultSet.getInt("id_user2");
                String friendsFrom = resultSet.getString("friendsfrom");
                Repository<Integer, User> repo = new UserDBRepository<>(new UserValidator());
                list.add(new Friendship(repo.get_all().get(id1), repo.get_all().get(id2),
                        LocalDateTime.parse(friendsFrom, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public void addFriendship(User user1, User user2) {
        String query = "INSERT INTO friendship(id_user1, id_user2, friendsfrom) VALUES(?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, user1.getId());
            statement.setInt(2, user2.getId());
            statement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeFriendships(User user1, User user2) {
        String query = "DELETE FROM friendship WHERE id_user1 = ? AND id_user2 = ?";
        try (Connection connection = jdbcUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, user1.getId());
            statement.setInt(2, user2.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Friendship getFriendship(User user1, User user2) {
        List<Friendship> list = getAll();
        for (var friends : list) {
            if (friends.getUser1() == user1 && friends.getUser2() == user2) {
                return friends;
            }
        }
        return null;
    }

    public List<FriendRequests> getFriendRequests(User user) {
        List<FriendRequests> list = new ArrayList<>();
        String query = "select * from \"friendRequests\"";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id1");
                Integer id2 = resultSet.getInt("id2");
                String status = resultSet.getString("staus");
                String from = resultSet.getString("requestFrom");
                Repository<Integer, User> repo = new UserDBRepository<>(new UserValidator());
                if (Objects.equals(repo.findOne(id1), user)) {
                    list.add(new FriendRequests(repo.get_all().get(id1), repo.get_all().get(id2), status,
                            LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<FriendRequests> getFriendRequestsSend(User user) {
        List<FriendRequests> list = new ArrayList<>();
        String query = "select * from \"friendRequests\"";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer id1 = resultSet.getInt("id1");
                Integer id2 = resultSet.getInt("id2");
                String status = resultSet.getString("staus");
                String from = resultSet.getString("requestFrom");
                Repository<Integer, User> repo = new UserDBRepository<>(new UserValidator());
                if (Objects.equals(repo.findOne(id2), user)) {
                    list.add(new FriendRequests(repo.get_all().get(id1), repo.get_all().get(id2), status,
                            LocalDateTime.parse(from, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"))));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addFriendRequest(User user1, User user2) {
        String query = "insert into \"friendRequests\"(id1, id2, \"requestFrom\", staus) VALUES(?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, user1.getId());
            statement.setInt(2, user2.getId());
            statement.setString(4, "Pending");
            statement.setString(3, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateFriendRequest(FriendRequests friendRequest) {
        String query = "UPDATE \"friendRequests\" SET \"requestFrom\"=?,staus=? WHERE id1=? and id2=?";

        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1, friendRequest.getTime().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
            statement.setString(2, friendRequest.getStatus());
            statement.setInt(3, friendRequest.getUser1().getId());
            statement.setInt(4, friendRequest.getUser2().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteFriendRequest(User user1, User user2) {
        String query = "delete from \"friendRequests\" where id1=? and id2=?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, user1.getId());
            statement.setInt(2, user2.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
