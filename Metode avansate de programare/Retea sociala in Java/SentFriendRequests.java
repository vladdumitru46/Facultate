package com.example.reteasocialagui;

import com.example.reteasocialagui.example.domain.FriendRequests;
import com.example.reteasocialagui.example.domain.SentFriendRequest;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SentFriendRequests implements Observer<FriendRequestChangeEvent> {

    private UserService service;
    private ObservableList<SentFriendRequest> friendsRequests = FXCollections.observableArrayList();
    List<FriendRequests> list = new ArrayList<>();
    List<SentFriendRequest> listSent = new ArrayList<>();

    @FXML
    ListView<SentFriendRequest> listView;

    Label userName = new Label();

    public void initialize() {
        //listSent = new ArrayList<>();
        for (var l : list) {
            SentFriendRequest sentFriendRequest = new SentFriendRequest(l.getUser1(), l.getUser2(), l.getStatus(), l.getTime());
            listSent.add(sentFriendRequest);
        }
        friendsRequests.addAll(listSent);
        listView.setItems(friendsRequests);
    }

    public void setList(List<FriendRequests> friendRequestsList) {
        list = friendRequestsList;
    }

    public void onUnsendPush(ActionEvent actionEvent) {
        List<String> stringList = service.convertNameTOFirstAndLastName(userName.getText());
        String firstName = stringList.get(0);
        String lastName = stringList.get(1);
        User your_user = service.findOneByName(firstName, lastName);
        SentFriendRequest sentFriendRequest = (SentFriendRequest) listView.getSelectionModel().getSelectedItem();
        if (Objects.equals(sentFriendRequest.getStatus(), "Pending")) {
            User sentRequestUser = sentFriendRequest.getUser1();
            for (var l : list) {
                if (Objects.equals(l.getUser1(), sentRequestUser) && Objects.equals(l.getUser2(), your_user)) {
                    list.remove(l);
                    System.out.println("hiagss");
                    break;
                }
            }
            service.deleteFriendRequest(sentRequestUser, your_user);
        }

    }

    public void setLabel(String userNameText) {
        userName.setText(userNameText);
    }

    public void setService(UserService userService) {
        this.service = userService;
        service.addObserver(this);
        initModel();
    }

    private void initModel() {
        listSent.clear();
        for (var l : list) {
            SentFriendRequest sentFriendRequest = new SentFriendRequest(l.getUser1(), l.getUser2(), l.getStatus(), l.getTime());
            listSent.add(sentFriendRequest);
        }
        friendsRequests.setAll(listSent);
    }

    @Override
    public void update(FriendRequestChangeEvent friendRequestChangeEvent) {
        initModel();
    }
}
