package com.example.reteasocialagui;

import com.example.reteasocialagui.example.domain.User;
import com.example.reteasocialagui.example.domain.validators.UserValidator;
import com.example.reteasocialagui.example.repository.database.FriendshipDBRepository;
import com.example.reteasocialagui.example.repository.database.MessageDBRepo;
import com.example.reteasocialagui.example.repository.database.UserDBRepository;
import com.example.reteasocialagui.example.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.security.NoSuchAlgorithmException;

public class SignUp {

    UserService service = new UserService(new UserDBRepository<Integer, User>(new UserValidator()), new FriendshipDBRepository(), new MessageDBRepo());

    @FXML
    private TextField firstNameTF;
    @FXML
    private TextField lastNameTF;

    @FXML
    private TextField emailTF;

    @FXML
    private TextField passwordTF;

    public void onSignUpPush(ActionEvent actionEvent) throws NoSuchAlgorithmException {
        int id = 1;
        for (var user : service.getAll().entrySet()) {
            if (id == user.getKey()) {
                id++;
            }
        }
        User user = new User(firstNameTF.getText(), lastNameTF.getText());
        user.setId(id);
        user.setEmail(emailTF.getText());
        user.setPassword(passwordTF.getText());
        service.add(id, firstNameTF.getText(), lastNameTF.getText(), emailTF.getText(), passwordTF.getText());
    }
}
