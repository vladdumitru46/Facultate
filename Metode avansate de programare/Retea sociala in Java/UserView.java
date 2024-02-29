package com.example.reteasocialagui;

import com.example.reteasocialagui.example.domain.Friendship;
import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.domain.UserFriendsFrom;
import com.example.reteasocialagui.example.domain.validators.UserValidator;
import com.example.reteasocialagui.example.events.UserChangeEvent;
import com.example.reteasocialagui.example.observer.ObserverU;
import com.example.reteasocialagui.example.repository.database.FriendshipDBRepository;
import com.example.reteasocialagui.example.repository.database.MessageDBRepo;
import com.example.reteasocialagui.example.repository.database.UserDBRepository;
import com.example.reteasocialagui.example.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserView implements ObserverU<UserChangeEvent> {

    private UserService service;
    private ObservableList<UserFriendsFrom> userFriendsFroms = FXCollections.observableArrayList();
    @FXML
    private ListView<UserFriendsFrom> listView;


    public String userNameText;
    List<UserFriendsFrom> userFriendsFrom = new ArrayList<>();

    List<Friendship> list = new ArrayList<>();


    public void setList(List<Friendship> l) {
        list = l;
    }

    public void setService(UserService userService) {
        this.service = userService;
        service.addObserverU(this);
        initModel();
    }

    public void initialize() {
        List<Friendship> friendships = list;
        for (var f : friendships) {
            userFriendsFrom.add(new UserFriendsFrom(f.getUser2().getId(), f.getUser2().getFirstName(), f.getUser2().getLastName(), f.getTime()));
        }
        userFriendsFroms.addAll(userFriendsFrom);
        listView.setItems(userFriendsFroms);
    }

    private void initModel() {
        userFriendsFrom.clear();
        List<String> l = service.convertNameTOFirstAndLastName(userNameText);
        String first_name = l.get(0);
        String last_name = l.get(1);
        List<Friendship> friendships = service.showFriends(service.findOneByName(first_name, last_name).getId());
        for (var f : friendships) {
            userFriendsFrom.add(new UserFriendsFrom(f.getUser2().getId(), f.getUser2().getFirstName(), f.getUser2().getLastName(), f.getTime()));
        }
        userFriendsFroms.setAll(userFriendsFrom);
    }

    public void setLabel(String labelTXT) {
        userNameText = labelTXT;
    }

    public void onFriendRequestPush(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("friendRequests.fxml"));
        Parent fxmlLoader = loader.load();
        FriendsRequests user = loader.getController();
        user.setLabel(userNameText);
        List<String> list = service.convertNameTOFirstAndLastName(userNameText);
        String first_name_f = list.get(0);
        String last_name_f = list.get(1);
        user.getList(service.getFriendRequests(service.findOneByName(first_name_f, last_name_f)));
        Scene scene = new Scene(fxmlLoader, 600, 400);
        stage.setScene(scene);
        user.setService(new UserService(new UserDBRepository<Integer, User>(new UserValidator()), new FriendshipDBRepository(), new MessageDBRepo()));
        stage.show();
    }


    public void onAddFriendPush(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("searchUserForRequest.fxml"));
        Parent fxmlLoader = loader.load();
        SearchUserForRequest user = loader.getController();
        user.setLabel(userNameText);
        Scene scene = new Scene(fxmlLoader, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public void onDeleteFriendPush(ActionEvent actionEvent) {
        UserFriendsFrom userFriendsFrom = (UserFriendsFrom) listView.getSelectionModel().getSelectedItem();
        if (userFriendsFrom != null) {
            User user = new User(userFriendsFrom.getFirstName(), userFriendsFrom.getLastName());
            user.setId(userFriendsFrom.getId());
            List<String> list1 = service.convertNameTOFirstAndLastName(userNameText);
            String first_name_f = list1.get(0);
            String last_name_f = list1.get(1);
            list.clear();
            list = service.showFriends(service.findOneByName(first_name_f, last_name_f).getId());
            service.deleteFriendship(user, service.findOneByName(first_name_f, last_name_f));
            service.deleteFriendship(service.findOneByName(first_name_f, last_name_f), user);
        }

    }

    public void onSentRequestsPush(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("sentFriendRequests.fxml"));
        Parent fxmlLoader = loader.load();
        SentFriendRequests user = loader.getController();
        user.setLabel(userNameText);
        List<String> list = service.convertNameTOFirstAndLastName(userNameText);
        String first_name_f = list.get(0);
        String last_name_f = list.get(1);
        user.setList(service.getFriendRequestsSend(service.findOneByName(first_name_f, last_name_f)));
        Scene scene = new Scene(fxmlLoader, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        user.setService(new UserService(new UserDBRepository<Integer, User>(new UserValidator()),
                new FriendshipDBRepository(), new MessageDBRepo()));
        stage.show();
    }

    public void onChatPush(ActionEvent actionEvent) throws IOException {
        UserFriendsFrom userFriendsFrom = (UserFriendsFrom) listView.getSelectionModel().getSelectedItem();
        if (userFriendsFrom != null) {
            User receiver = new User(userFriendsFrom.getFirstName(), userFriendsFrom.getLastName());
            receiver.setId(userFriendsFrom.getId());
            List<String> list = service.convertNameTOFirstAndLastName(userNameText);
            String firstName = list.get(0);
            String lastName = list.get(1);
            User sender = service.findOneByName(firstName, lastName);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("message.fxml"));
            Parent fxmlLoader = loader.load();
            Message user = loader.getController();
            user.setSender(sender);
            user.setReceiver(receiver);
            user.setList(service.getMessagesBetweenUsers(sender, receiver));
            Scene scene = new Scene(fxmlLoader, 600, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            user.setService(new UserService(new UserDBRepository<Integer, User>(new UserValidator()),
                    new FriendshipDBRepository(), new MessageDBRepo()));
            stage.show();
        }
    }

    @Override
    public void update(UserChangeEvent userChangeEvent) {
        initModel();
    }
}
