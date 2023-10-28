package com.example.reteasocialagui.example.service;

import com.example.reteasocialagui.example.domain.*;

import java.util.List;
import java.util.Map;

public interface Service<ID, E extends Entity<ID>> {
    /**
     * @param id -the id of the entity to be returned
     *           id must not be null
     * @return the entity with the specified id
     * or null - if there is no entity with the given id
     * @throws IllegalArgumentException if id is null.
     */
    E findOne(ID id);

    E findOneByName(String firstName, String lastName);

    /**
     * @return all entities
     */
    Iterable<E> findAll();

    Map<Integer, User> getAll();

    /**
     * @param id, first_name, second_name, entity must be not null
     * @return null- if the given entity is saved
     * otherwise returns the entity (id already exists)
     * @throws ValidationException      if the entity is not valid
     * @throws IllegalArgumentException if the given entity is null.     *
     */
    E add(ID id, String first_name, String second_name, String email, String password);


    /**
     * removes the entity with the specified id
     *
     * @param id id must be not null
     * @return the removed entity or null if there is no entity with the given id
     * @throws IllegalArgumentException if the given id is null.
     */
    E delete(ID id);

    List<User> findUserWIthName(String name);

    E update(ID id, String firstname, String secondname, String email, String password);

    void addFriendship(User user1, User user2);

    void deleteFriendship(User user1, User user2);

    List<Friendship> showFriends(int id);

    User findOneByEmailANdPassword(String email, String password);

    int communitiesNumber();

    List<User> longestUserRoad();

    void updateFriendRequest(FriendRequests friendRequest);

    void CreateList(Integer id);

    List<FriendRequests> getFriendRequests(User user);

    List<FriendRequests> getFriendRequestsSend(User user);

    void deleteFriendRequest(User user1, User user2);

    List<Messages> getMessagesBetweenUsers(User sender, User receiver);

    Friendship getFriendship(User user1, User user2);

    void sendMessage(User sender, User receiver, String message);

    void deleteMessage(User sender, User receiver, String message);
}

