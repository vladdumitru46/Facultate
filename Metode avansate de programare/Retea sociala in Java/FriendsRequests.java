package com.example.reteasocialagui;

import com.example.reteasocialagui.example.domain.FriendRequests;
import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.events.FriendRequestChangeEvent;
import com.example.reteasocialagui.example.observer.Observer;
import com.example.reteasocialagui.example.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FriendsRequests implements Observer<FriendRequestChangeEvent> {

    private UserService service;
    ObservableList<FriendRequests> friendRequests = FXCollections.observableArrayList();
    @FXML
    ListView<FriendRequests> listOfFriendRequests;
    List<FriendRequests> list = new ArrayList<>();
    Label userName = new Label();

    public void initialize() {
        friendRequests.addAll(list);
        listOfFriendRequests.setItems(friendRequests);
    }

    public void onAcceptPush(ActionEvent actionEvent) {
        FriendRequests friendRequests = (FriendRequests) listOfFriendRequests.getSelectionModel().getSelectedItem();
        if (friendRequests != null && friendRequests.getStatus().equals("Pending")) {
            List<String> listOfString = service.convertNameTOFirstAndLastName(userName.getText());
            String firstName = listOfString.get(0);
            String lastName = listOfString.get(1);
            User user = service.findOneByName(firstName, lastName);
            User user1 = friendRequests.getUser2();
            service.addFriendship(user, user1);
            service.addFriendship(user1, user);
            friendRequests.setStatus("Accepted");
            friendRequests.setTime(LocalDateTime.now());
            service.updateFriendRequest(friendRequests);
        }
    }

    public void onRemovePush(ActionEvent actionEvent) {
        FriendRequests friendRequests = (FriendRequests) listOfFriendRequests.getSelectionModel().getSelectedItem();
        if (friendRequests != null && Objects.equals(friendRequests.getStatus(), "Pending")) {
            List<String> listOfString = service.convertNameTOFirstAndLastName(userName.getText());
            String firstName = listOfString.get(0);
            String lastName = listOfString.get(1);
            User user = service.findOneByName(firstName, lastName);
            User user1 = friendRequests.getUser2();
            service.deleteFriendship(user, user1);
            service.deleteFriendship(user1, user);
            friendRequests.setStatus("Rejected");
        }
    }

    public void setLabel(String userNameText) {
        userName.setText(userNameText);
    }

    public void getList(List<FriendRequests> listUsers) {
        list = listUsers;
    }


    public void setService(UserService service) {
        this.service = service;
        service.addObserver(this);
        initModel();
    }

    @Override
    public void update(FriendRequestChangeEvent userChangeEvent) {
        initModel();
    }

    private void initModel() {
//        Iterable<FriendRequests> friendRequests1 = list;
//        List<FriendRequests> friendRequests2 = StreamSupport.stream(friendRequests1.spliterator(), false).collect(Collectors.toList());
        friendRequests.setAll(list);
    }

}
