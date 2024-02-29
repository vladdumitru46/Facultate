package com.example.reteasocialagui.example.repository;

import com.example.reteasocialagui.example.domain.FriendRequests;
import com.example.reteasocialagui.example.domain.Friendship;
import com.example.reteasocialagui.example.domain.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RepoFriendship {
    void addFriendship(User user1, User user2);

    void createList(Integer id);

    void removeFriendships(User user1, User user2);

    void removeFriendList(Integer id);

    void removeListOfFriends(Integer id);

    Set<Map.Entry<Integer, Set<User>>> getEntries();

    Set<User> showFriends(Integer id);

    Map<Integer, Set<User>> get_all();

    List<Friendship> getAll();

    List<Friendship> getFriendships(Integer id);

    Friendship getFriendship(User user1, User user2);

    List<FriendRequests> getFriendRequests(User user);

    List<FriendRequests> getFriendRequestsSend(User user);

    void updateFriendRequest(FriendRequests friendRequest);

    void addFriendRequest(User user1, User user2);

    void deleteFriendRequest(User user1, User user2);
}
