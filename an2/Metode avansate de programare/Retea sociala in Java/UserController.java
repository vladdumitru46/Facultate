package com.example.reteasocialagui;

import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.domain.validators.UserValidator;
import com.example.reteasocialagui.example.repository.database.FriendshipDBRepository;
import com.example.reteasocialagui.example.repository.database.MessageDBRepo;
import com.example.reteasocialagui.example.repository.database.UserDBRepository;
import com.example.reteasocialagui.example.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserController {

    private UserService service = new UserService(new UserDBRepository<Integer, User>(new UserValidator()), new FriendshipDBRepository(), new MessageDBRepo());

    @FXML
    protected TextField userNameText;
    @FXML
    private TextField passwordText;

    @FXML
    private Label label;

    public void onSignInPush(ActionEvent actionEvent) throws IOException {
//        List<String> l = service.convertNameTOFirstAndLastName(userNameText.getText());
//        String first_name = l.get(0);
//        String last_name = l.get(1);

        if (service.findOneByEmailANdPassword(userNameText.getText(), passwordText.getText()) != null) {
            label.setText("");
            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("userView.fxml"));
            Parent fxmlLoader = loader.load();
            UserView user = loader.getController();
            User u = service.findOneByEmailANdPassword(userNameText.getText(), passwordText.getText());
            String name = u.getFirstName() + " " + u.getLastName();
            user.setLabel(name);

            Scene scene = new Scene(fxmlLoader, 600, 400);
            stage.setTitle(userNameText.getText());
            stage.setScene(scene);
            user.setList(service.showFriends(service.findOneByName(u.getFirstName(), u.getLastName()).getId()));
            user.setService(new UserService(new UserDBRepository<Integer, User>(new UserValidator()), new FriendshipDBRepository(), new MessageDBRepo()));
            stage.show();
        } else {
            label.setText("The account does not exist!");
        }

    }

    public int onClosePush(ActionEvent actionEvent) {
        return 0;
    }

    public void onSignUnPush(ActionEvent actionEvent) throws IOException {
        label.setText("");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("signUp.fxml"));
        Parent fxmlLoader = loader.load();
        Scene scene = new Scene(fxmlLoader, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Sign up!");
        stage.setScene(scene);
        stage.show();
    }

    public void onForgetPush(ActionEvent actionEvent) throws IOException {
        label.setText("");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forgotPassword.fxml"));
        Parent fxmlLoader = loader.load();
        Scene scene = new Scene(fxmlLoader, 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Sign up!");
        stage.setScene(scene);
        stage.show();
    }
}