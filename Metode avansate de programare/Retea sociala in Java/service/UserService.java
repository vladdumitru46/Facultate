package com.example.reteasocialagui.example.service;

import com.example.reteasocialagui.example.domain.*;
import com.example.reteasocialagui.example.domain.validators.UserValidator;
import com.example.reteasocialagui.example.domain.validators.ValidationException;
import com.example.reteasocialagui.example.domain.validators.Validator;
import com.example.reteasocialagui.example.events.ChangeEventType;
import com.example.reteasocialagui.example.events.FriendRequestChangeEvent;
import com.example.reteasocialagui.example.events.MessageChangeEvent;
import com.example.reteasocialagui.example.events.UserChangeEvent;
import com.example.reteasocialagui.example.observer.Observable;
import com.example.reteasocialagui.example.observer.Observer;
import com.example.reteasocialagui.example.observer.*;
import com.example.reteasocialagui.example.repository.RepoFriendship;
import com.example.reteasocialagui.example.repository.RepoMessages;
import com.example.reteasocialagui.example.repository.Repository;

import java.util.*;

public class UserService implements Service<Integer, User>, Observable<FriendRequestChangeEvent>, ObservableU<UserChangeEvent>, ObservableM<MessageChangeEvent> {
    private final Repository<Integer, User> repository;

    private final RepoFriendship friendship;
    private final RepoMessages messages;

    private final List<Observer<FriendRequestChangeEvent>> observers = new ArrayList<>();
    private final List<ObserverM<MessageChangeEvent>> observerMS = new ArrayList<>();

    private final List<ObserverU<UserChangeEvent>> observerU = new ArrayList<>();

    public UserService(Repository<Integer, User> repository, RepoFriendship friendship, RepoMessages messages) {
        this.repository = repository;
        this.friendship = friendship;
        this.messages = messages;
    }

    @Override
    public void addObserver(Observer<FriendRequestChangeEvent> e) {
        observers.add(e);
    }

    @Override
    public void removeObserver(Observer<FriendRequestChangeEvent> e) {
        observers.remove(e);
    }

    @Override
    public void notifyObservers(FriendRequestChangeEvent t) {
        observers.forEach(x -> x.update(t));
    }

    @Override
    public void addObserverU(ObserverU<UserChangeEvent> e) {
        observerU.add(e);
    }

    @Override
    public void removeObserverU(ObserverU<UserChangeEvent> e) {
        observerU.remove(e);
    }

    @Override
    public void notifyObserversU(UserChangeEvent t) {
        observerU.forEach(x -> x.update(t));
    }

    @Override
    public void addObserverM(ObserverM<MessageChangeEvent> e) {
        observerMS.add(e);
    }

    @Override
    public void removeObserverM(ObserverM<MessageChangeEvent> e) {
        observerMS.remove(e);
    }

    @Override
    public void notifyObserversM(MessageChangeEvent t) {
        observerMS.forEach(x -> x.update(t));
    }

    @Override
    public User findOne(Integer integer) {
        return repository.findOne(integer);
    }

    public List<User> findUserWIthName(String name) {
        List<User> userList = new ArrayList<>();
        int ok = 1, nr;
        for (var user : repository.findAll()) {
            String userName = user.getFirstName() + " " + user.getLastName();
            for (int i = 0; i < userName.length() - name.length(); i++) {
                ok = 1;
                nr = 0;
                for (int j = 0; j < name.length() && ok == 1; j++) {
                    nr++;
                    if (userName.charAt(i + j) != name.charAt(j)) {
                        ok = 0;
                    }
                }
                if (ok == 1 && nr == name.length()) {
                    break;
                }
            }
            if (ok == 1) {
                userList.add(user);
            }
        }
        return userList;
    }

    @Override
    public User findOneByName(String firstName, String lastName) {
        return repository.findOneByName(firstName, lastName);
    }

    @Override
    public Iterable<User> findAll() {
        return repository.findAll();
    }

    public Map<Integer, User> getAll() {
        return repository.get_all();
    }

    @Override
    public User add(Integer id, String first_name, String second_name, String email, String password) {
        User user = new User(first_name, second_name);
        Validator<User> validator = new UserValidator();
        validator.validate(user);
        user.setEmail(email);
        user.setPassword(password);
        // this.notifyObserver(new UserChangeEvent(ChangeEventType.ADD, user));
        return repository.save(id, user);
    }

    @Override
    public User delete(Integer id) {
        if (repository.findOne(id) == null) {
            throw new IllegalArgumentException("The user does not exist!\n");
        }
        List<Friendship> friends = friendship.getAll();
        for (var friend : friends) {
            friendship.removeFriendships(friend.getUser1(), repository.findOne(id));
            friendship.removeFriendships(repository.findOne(id), friend.getUser1());
        }
        friendship.removeFriendList(id);
        friendship.removeListOfFriends(id);
//        User user = repository.findOne(id);
//       // this.notifyObserver(new UserChangeEvent(ChangeEventType.DELETE, user));
        return repository.delete(id);
    }


    public User update(Integer id, String firstname, String lastName, String email, String password) {
        User user = new User(firstname, lastName);
        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
//        this.notifyObserver(new UserChangeEvent(ChangeEventType.UPDATE, user));
        return repository.update(user);
    }

    public Friendship getFriendship(User user1, User user2) {
        return friendship.getFriendship(user1, user2);
    }

    @Override
    public void addFriendship(User user1, User user2) {
        UserFriendsFrom userFriendsFrom = null;
        if (repository.get_all().get(user1.getId()) == null || repository.get_all().get(user2.getId()) == null)
            throw new IllegalArgumentException("User invalid!\n");
        friendship.addFriendship(user1, user2);
        for (var f : friendship.getFriendships(user1.getId())) {
            if (Objects.equals(f.getUser2(), user2)) {
                userFriendsFrom = new UserFriendsFrom(f.getUser2().getId(), f.getUser2().getFirstName(), f.getUser2().getLastName(), f.getTime());
            }
        }
        this.notifyObserversU(new UserChangeEvent(ChangeEventType.ADD, userFriendsFrom));
    }

    public List<Friendship> showFriends(int id) {
        return friendship.getFriendships(id);
    }

    @Override
    public void deleteFriendship(User user1, User user2) {
        UserFriendsFrom userFriendsFrom = null;
        if (repository.get_all().get(user2.getId()) == null || repository.get_all().get(user2.getId()) == null)
            throw new ValidationException("The user does not exist!\n");
        for (var f : friendship.getFriendships(user1.getId())) {
            if (Objects.equals(f.getUser2(), user2)) {
                userFriendsFrom = new UserFriendsFrom(f.getUser2().getId(), f.getUser2().getFirstName(), f.getUser2().getLastName(), f.getTime());
            }
        }
        friendship.removeFriendships(user1, user2);
        friendship.removeFriendships(user2, user1);
        this.notifyObserversU(new UserChangeEvent(ChangeEventType.DELETE, userFriendsFrom));
    }

    @Override
    public List<User> longestUserRoad() {
        List<Integer> list = longestRoad();
        List<User> user_list = new ArrayList<>();

        for (var user : list) {
            user_list.add(repository.findOne(user));
        }
        return user_list;
    }

    public void CreateList(Integer id) {
        friendship.createList(id);
    }

    public static void dfs(Integer id, Map<Integer, Set<User>> user_map, List<Boolean> booleanList) {
        booleanList.set(id, true);
        Set<User> neighbours = user_map.get(id);
        for (var users : neighbours) {
            if (!booleanList.get(users.getId())) {
                dfs(users.getId(), user_map, booleanList);
            }
        }
    }

    public int communitiesNumber() {
        List<Boolean> booleanList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            booleanList.add(false);
        }
        int numberOfCommunities = 0;
        for (var entry : friendship.get_all().entrySet()) {
            if (!booleanList.get(entry.getKey())) {
                numberOfCommunities++;
                dfs(entry.getKey(), friendship.get_all(), booleanList);
            }
        }
        return numberOfCommunities;
    }

    private static final List<Integer> longest_road = new ArrayList<>();

    private static int lenMax = 0;

    public void dfs2(Integer id, Map<Integer, Set<User>> user_map, List<Boolean> booleanList, List<Integer> list, int len) {
        len++;
        list.add(id);
        booleanList.set(id, true);
        Set<User> neighbours = user_map.get(id);
        for (var users : neighbours) {
            if (!booleanList.get(users.getId())) {
                dfs2(users.getId(), user_map, booleanList, list, len);
                booleanList.set(users.getId(), false);
                list.remove(list.size() - 1);
            }
        }
        if (lenMax < len) {
            lenMax = len;
            longest_road.clear();
            longest_road.addAll(list);
        }
    }

    public List<Integer> longestRoad() {
        longest_road.clear();
        lenMax = 0;
        List<Integer> list = new ArrayList<>();
        List<Boolean> booleanList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            booleanList.add(false);
        }
        for (var entry : friendship.getEntries()) {
            dfs2(entry.getKey(), friendship.get_all(), booleanList, list, 0);
            booleanList.set(entry.getKey(), false);
            list.remove(list.size() - 1);
        }
        return longest_road;
    }

    public List<String> convertNameTOFirstAndLastName(String nameAddText) {
        List<String> l = new ArrayList<>();
        String first_name = "";
        String last_name = "";
        int ok = 1;
        for (int i = 0; i < nameAddText.length(); i++) {
            if (nameAddText.charAt(i) != ' ' && ok == 1) {
                first_name += nameAddText.charAt(i);
            } else if (nameAddText.charAt(i) == ' ') {
                ok = 0;
            } else if (ok == 0) {
                last_name += nameAddText.charAt(i);
            }
        }
        l.add(first_name);
        l.add(last_name);
        return l;
    }

    public List<FriendRequests> getFriendRequests(User user) {
        return friendship.getFriendRequests(user);
    }

    public List<FriendRequests> getFriendRequestsSend(User user) {
        return friendship.getFriendRequestsSend(user);
    }

    @Override
    public void deleteFriendRequest(User user_sender, User user_receiver) {
        FriendRequests friendRequests = null;
        for (var f : getFriendRequests(user_sender)) {
            if (Objects.equals(f.getUser2().getId(), user_receiver.getId())) {
                friendRequests = f;
            }
        }
        friendship.deleteFriendRequest(user_sender, user_receiver);
        this.notifyObservers(new FriendRequestChangeEvent(ChangeEventType.DELETE, friendRequests));
    }


    public void addFriendRequest(User user1, User user2) {
        friendship.addFriendRequest(user1, user2);
        for (var f : getFriendRequests(user1)) {
            if (Objects.equals(f.getUser2().getId(), user2.getId())) {
                this.notifyObservers(new FriendRequestChangeEvent(ChangeEventType.ADD, f));
            }
        }

    }

    @Override
    public void updateFriendRequest(FriendRequests friendRequest) {
        friendship.updateFriendRequest(friendRequest);
        this.notifyObservers(new FriendRequestChangeEvent(ChangeEventType.UPDATE, friendRequest));
    }

    public User findOneByEmailANdPassword(String email, String password) {
        return repository.findONeByEmailAndPassword(email, password);
    }


    public List<Messages> getMessagesBetweenUsers(User sender, User receiver) {
        return messages.getMessagesBetweenUsers(sender, receiver);
    }

    @Override
    public void sendMessage(User sender, User receiver, String message) {
        messages.sendMessage(sender, receiver, message);
        Messages messages1 = new Messages(sender, receiver, message);
        this.notifyObserversM(new MessageChangeEvent(ChangeEventType.ADD, messages1));
        Messages messages2 = new Messages(receiver, sender, message);
        this.notifyObserversM(new MessageChangeEvent(ChangeEventType.DELETE, messages2));

    }

    @Override
    public void deleteMessage(User sender, User receiver, String message) {
        messages.deleteMessage(sender, receiver, message);
        Messages messages1 = new Messages(sender, receiver, message);
        this.notifyObserversM(new MessageChangeEvent(ChangeEventType.DELETE, messages1));
        Messages messages2 = new Messages(receiver, sender, message);
        this.notifyObserversM(new MessageChangeEvent(ChangeEventType.DELETE, messages2));
    }
}

