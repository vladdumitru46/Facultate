package com.example.reteasocialagui.example.repository.memory;

import com.example.reteasocialagui.example.domain.FriendRequests;
import com.example.reteasocialagui.example.domain.Friendship;
import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.repository.RepoFriendship;

import java.time.LocalDateTime;
import java.util.*;

public class FriendshipRepository implements RepoFriendship {

    protected Map<Integer, Set<User>> map;
    List<Friendship> list;
    LocalDateTime time = LocalDateTime.now();

    public FriendshipRepository() {
        this.map = new TreeMap<>();
        this.list = new ArrayList<>();
    }


    public void addFriendship(User user1, User user2) {
        if (!map.containsKey(user1.getId())) {
            Set<User> users = new HashSet<>();
            map.put(user1.getId(), users);
        }
        Set<User> users = new HashSet<>();
        map.get(user1.getId()).add(user2);
        if (!map.containsKey(user2.getId())) {
            map.put(user2.getId(), users);
        }
        map.get(user2.getId()).add(user1);


        int nr = 0;
        for (var user : list) {
            if (user.getUser1() == user1 && user.getUser2() == user2) {
                break;
            } else {
                nr++;
            }
        }
        if (nr == list.size()) {
            Friendship friendship = new Friendship(user1, user2, time);
            list.add(friendship);
            Friendship friendship2 = new Friendship(user2, user1, time);
            list.add(friendship2);
        }
    }

    public void createList(Integer id) {
        Set<User> l = new HashSet<>();
        map.put(id, l);
    }

    public void removeFriendships(User user1, User user2) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUser1() == user1 && list.get(i).getUser2() == user2) {
                list.remove(i);
            }
            if (list.get(i).getUser1() == user2 && list.get(i).getUser2() == user1) {
                list.remove(i);
            }
        }
        if (map.containsKey(user1.getId())) {
            map.get(user1.getId()).remove(user2);
            map.get(user2.getId()).remove(user1);
        }

    }


    public void removeFriendList(Integer id) {
        map.remove(id);
    }

    public void removeListOfFriends(Integer id) {
        for (var friends : getAll()) {
            if (Objects.equals(id, friends.getUser1().getId()) || Objects.equals(id, friends.getUser2().getId())) {
                getAll().remove(friends);
            }
        }
    }

    public Set<Map.Entry<Integer, Set<User>>> getEntries() {
        return get_all().entrySet();
    }

    public Set<User> showFriends(Integer id) {
        Set<User> l = map.get(id);
        if (l == null) {
            l = new HashSet<>();
        }
        return l;
    }

    public Map<Integer, Set<User>> get_all() {
        return map;
    }

    public List<Friendship> getAll() {
        return list;
    }

    public List<Friendship> getFriendships(Integer id) {
        List<Friendship> list_user = new ArrayList<>();
        for (var user : getAll()) {
            if (user.getUser1().getId().equals(id)) {
                list_user.add(user);
            }
        }
        return list_user;
    }

    @Override
    public Friendship getFriendship(User user1, User user2) {
        return null;
    }

    @Override
    public List<FriendRequests> getFriendRequests(User user) {
        return null;
    }

    @Override
    public List<FriendRequests> getFriendRequestsSend(User user) {
        return null;
    }

    @Override
    public void updateFriendRequest(FriendRequests friendRequest) {

    }

    @Override
    public void addFriendRequest(User user1, User user2) {

    }

    @Override
    public void deleteFriendRequest(User user1, User user2) {

    }


}
