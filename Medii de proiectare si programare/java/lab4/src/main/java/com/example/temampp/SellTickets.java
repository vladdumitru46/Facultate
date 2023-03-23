package com.example.temampp;

import com.example.temampp.domains.Show;
import com.example.temampp.repositoryes.ArtistRepo;
import com.example.temampp.repositoryes.BuyerRepo;
import com.example.temampp.repositoryes.EmployeeRepo;
import com.example.temampp.repositoryes.ShowRepository;
import com.example.temampp.services.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Properties;

public class SellTickets {

    Service service = new Service(new ArtistRepo(new Properties()), new BuyerRepo(new Properties()), new EmployeeRepo(new Properties()), new ShowRepository(new Properties()));
    @FXML
    TextField nameTF;
    @FXML
    TextField ticketsTF;

    @FXML
    Label label;

    Stage stage = new Stage();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setLabel(String text) {
        label.setText(text);
    }

    public void onSellPush(ActionEvent actionEvent) {
        String name = nameTF.getText();
        Integer noOfTickets = Integer.parseInt(ticketsTF.getText());
        String showName = label.getText();
        Show show = service.findOneShow(showName);
        show.setNoTicketsAvailable(show.getNoTicketsAvailable() - noOfTickets);
        show.setNoTicketsSold(show.getNoTicketsSold() + noOfTickets);
        service.updateShow(show);
        service.sellTickets(showName, name, noOfTickets);
    }

    public void onBackPush(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent loader = fxmlLoader.load();
        MainPage mainPage = fxmlLoader.getController();
        Scene scene = new Scene(loader, 600, 400);
        mainPage.setService(new Service(new ArtistRepo(new Properties()), new BuyerRepo(new Properties()), new EmployeeRepo(new Properties()), new ShowRepository(new Properties())));
        mainPage.setStage(stage);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
