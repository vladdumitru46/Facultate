package com.example.reteasocialagui;

import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.domain.validators.UserValidator;
import com.example.reteasocialagui.example.repository.database.FriendshipDBRepository;
import com.example.reteasocialagui.example.repository.database.MessageDBRepo;
import com.example.reteasocialagui.example.repository.database.UserDBRepository;
import com.example.reteasocialagui.example.service.UserService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.List;

public class SearchUserForRequest {

    private UserService service = new UserService(new UserDBRepository<Integer, User>(new UserValidator()), new FriendshipDBRepository(), new MessageDBRepo());
    private ObservableList<User> userList = FXCollections.observableArrayList();
    @FXML
    private TextField searchBar;
    @FXML
    ListView<User> searchListView;

    @FXML
    Label labelNoExist;

    Label userName = new Label();

    public void setLabel(String newName) {
        userName.setText(newName);
    }

    public void onSendPush(ActionEvent actionEvent) {
        List<String> l = service.convertNameTOFirstAndLastName(userName.getText());
        String first_name = l.get(0);
        String last_name = l.get(1);
        User userSend = (User) searchListView.getSelectionModel().getSelectedItem();
        if (userSend != null) {
            System.out.println(userSend + "\n");
            User user = service.findOneByName(first_name, last_name);
            System.out.println(user);
            service.addFriendRequest(userSend, user);
        }
    }

    public void onSearchPush(ActionEvent actionEvent) {
        labelNoExist.setText("");
        if (service.findUserWIthName(searchBar.getText()).size() == 0) {
            labelNoExist.setText("NO USER WITH THAT NAME!");
        } else {
            userList.setAll(service.findUserWIthName(searchBar.getText()));
            searchListView.setItems(userList);
        }
    }
}
