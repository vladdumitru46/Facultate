package com.example.reteasocialagui;

import com.example.reteasocialagui.example.domain.validators.UserValidator;
import com.example.reteasocialagui.example.repository.database.FriendshipDBRepository;
import com.example.reteasocialagui.example.repository.database.MessageDBRepo;
import com.example.reteasocialagui.example.repository.database.UserDBRepository;
import com.example.reteasocialagui.example.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Objects;

public class ForgotPassword {

    UserService service = new UserService(new UserDBRepository<>(new UserValidator()), new FriendshipDBRepository(), new MessageDBRepo());
    @FXML
    private TextField emailTF;
    @FXML
    private TextField passwordTF;

    public void onResetPush(ActionEvent actionEvent) {
        for (var u : service.findAll()) {
            if (Objects.equals(u.getEmail(), emailTF.getText())) {
                service.update(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(), passwordTF.getText());
            }
        }
    }
}
