package com.example.reteasocialagui;

import com.example.reteasocialagui.example.domain.Messages;
import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.events.MessageChangeEvent;
import com.example.reteasocialagui.example.observer.ObserverM;
import com.example.reteasocialagui.example.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class Message implements ObserverM<MessageChangeEvent> {

    private UserService service;
    ObservableList<Messages> messageObserver = FXCollections.observableArrayList();
    @FXML
    ListView<Messages> messageListView;
    @FXML
    TextField messageTF;

    List<Messages> list = new ArrayList<>();

    User userSender;
    User userReceiver;

    public void setList(List<Messages> m) {
        list = m;
    }

    public void onSendPush(ActionEvent actionEvent) {
        list.add(new Messages(userSender, userReceiver, messageTF.getText()));
        service.sendMessage(userSender, userReceiver, messageTF.getText());
        messageTF.setText("");
    }

    public void setService(UserService userService) {
        this.service = userService;
        service.addObserverM(this);
        initModel();
    }

    public void initialize() {
        messageObserver.addAll(list);
        messageListView.setItems(messageObserver);
    }

    private void initModel() {
        messageObserver.setAll(list);
    }

    public void setSender(User sender) {
        userSender = sender;
    }

    public void setReceiver(User receiver) {
        userReceiver = receiver;
    }

    @Override
    public void update(MessageChangeEvent messageChangeEvent) {
        initModel();
    }
}
